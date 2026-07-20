package maboutique.shop.boutiqueservice.gestionBoutique.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande.CommandeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.paiement.PaiementRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.panier.PanierDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.panier.PanierRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitPanierRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.*;
import maboutique.shop.boutiqueservice.gestionBoutique.enums.StatutCommande;
import maboutique.shop.boutiqueservice.gestionBoutique.enums.StatutPaiement;
import maboutique.shop.boutiqueservice.gestionBoutique.exceptions.PaiementRefuseException;
import maboutique.shop.boutiqueservice.gestionBoutique.exceptions.ResourceNotFoundException;
import maboutique.shop.boutiqueservice.gestionBoutique.exceptions.StockInsuffisantException;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.CommandeMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.PanierMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.ProduitCommandeMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.ProduitPanierMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.*;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.PanierService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;
    private final UsersRepository userRepository;
    private final ProduitRepository produitRepository;
    private final PanierMapper panierMapper;
    private final ProduitPanierMapper produitPanierMapper;
    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;
    private final ProduitCommandeMapper produitCommandeMapper;
    private final PaiementRepository paiementRepository;

    @Override
    @Transactional
    public PanierDto creerPanier(PanierRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

        Panier panier = new Panier();
        panier.setUser(user);
        panier.setProduits(new ArrayList<>());

        Panier saved = panierRepository.save(panier);
        return panierMapper.toDto(saved);
    }

    @Override
    @Transactional
    public PanierDto ajouterProduit(UUID panierId, ProduitPanierRequestDto produitDto) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new ResourceNotFoundException("Panier introuvable"));

        Produit produit = produitRepository.findById(produitDto.getProduitId())
                .orElseThrow(() -> new ResourceNotFoundException("Produit introuvable"));

        ProduitPanier produitPanier = new ProduitPanier();
        produitPanier.setPanier(panier);
        produitPanier.setProduit(produit);
        produitPanier.setQuantite(produitDto.getQuantite());

        panier.getProduits().add(produitPanier);

        Panier saved = panierRepository.save(panier);
        return panierMapper.toDto(saved);
    }

    @Override
    @Transactional
    public PanierDto retirerProduit(UUID panierId, UUID produitPanierId) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new ResourceNotFoundException("Panier introuvable"));

        panier.getProduits().removeIf(p -> p.getId().equals(produitPanierId));

        Panier saved = panierRepository.save(panier);
        return panierMapper.toDto(saved);
    }

    @Override
    public PanierDto trouverParId(UUID id) {
        Panier panier = panierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Panier introuvable"));
        return panierMapper.toDto(panier);
    }

    @Override
    public List<PanierDto> listerPaniersParUser(UUID userId) {
        return panierMapper.toDto(panierRepository.findByUserId(userId));
    }

    @Override
    @Transactional
    public void supprimerPanier(UUID id) {
        Panier panier = panierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Panier introuvable"));
        panierRepository.delete(panier);
    }

    @Override
    @Transactional
    public CommandeDto validerPanier(UUID panierId) {

        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new ResourceNotFoundException("Panier introuvable"));

        Commande commande = new Commande();
        commande.setUser(panier.getUser());
        commande.setStatut(StatutCommande.EN_ATTENTE);

        // Transformer ProduitPanier → ProduitCommande
        List<ProduitCommande> produitsCommande = panier.getProduits().stream()
                .map(pp -> {
                    Produit produit = pp.getProduit();

                    // Vérification du stock
                    if (produit.getStock() < pp.getQuantite()) {
                        throw new StockInsuffisantException(
                                "Stock insuffisant pour le produit " + produit.getNomProduit()
                        );
                    }

                    // Décrémenter le stock
                    produit.setStock(produit.getStock() - pp.getQuantite());
                    produitRepository.save(produit);

                    ProduitCommande pc = new ProduitCommande();
                    pc.setCommande(commande);
                    pc.setProduit(pp.getProduit());
                    pc.setQuantite(pp.getQuantite());
                    pc.setPrixUnitaire(pp.getProduit().getPrix());
                    pc.setSousTotal(pp.getProduit().getPrix()
                            .multiply(BigDecimal.valueOf(pp.getQuantite())));
                    return pc;
                })
                .toList();

        commande.setProduits(produitsCommande);

        // Calcul du montant total
        BigDecimal montantTotal = produitsCommande.stream()
                .map(ProduitCommande::getSousTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        commande.setMontantTotal(montantTotal);

        Commande savedCommande = commandeRepository.save(commande);

        // Déclencher le paiement
        PaiementRequestDto paiementRequest = PaiementRequestDto.builder()
                .commandeId(savedCommande.getId())
                .montant(savedCommande.getMontantTotal())
                .methodePaiement("CARTE")
                .referencePaiement(UUID.randomUUID().toString())
                .build();

        if (paiementRequest.getStatut() != StatutPaiement.EFFECTUE) {
            throw new PaiementRefuseException("Le paiement a été refusé.");
        }

        // Supprimer le panier une fois validé
        panierRepository.delete(panier);

        return commandeMapper.toDto(savedCommande);
    }
}



