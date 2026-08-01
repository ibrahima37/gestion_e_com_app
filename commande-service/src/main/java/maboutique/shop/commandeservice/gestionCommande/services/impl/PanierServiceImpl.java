package maboutique.shop.commandeservice.gestionCommande.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import maboutique.shop.commandeservice.gestionCommande.dtos.commande.CommandeDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.PanierDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.PanierRequestDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.ProduitPanierRequestDto;
import maboutique.shop.commandeservice.gestionCommande.entities.Commande;
import maboutique.shop.commandeservice.gestionCommande.entities.Panier;
import maboutique.shop.commandeservice.gestionCommande.entities.ProduitCommande;
import maboutique.shop.commandeservice.gestionCommande.entities.ProduitPanier;
import maboutique.shop.commandeservice.gestionCommande.enums.StatutCommande;
import maboutique.shop.commandeservice.gestionCommande.mappers.ints.CommandeMapper;
import maboutique.shop.commandeservice.gestionCommande.mappers.ints.PanierMapper;
import maboutique.shop.commandeservice.gestionCommande.mappers.ints.ProduitCommandeMapper;
import maboutique.shop.commandeservice.gestionCommande.mappers.ints.ProduitPanierMapper;
import maboutique.shop.commandeservice.gestionCommande.repositories.CommandeRepository;
import maboutique.shop.commandeservice.gestionCommande.repositories.PanierRepository;
import maboutique.shop.commandeservice.gestionCommande.repositories.ProduitPanierRepository;
import maboutique.shop.commandeservice.gestionCommande.services.ints.PanierService;
import maboutique.shop.commonentities.gestionCommon.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;
    private final PanierMapper panierMapper;
    private final ProduitPanierMapper produitPanierMapper;
    private final CommandeRepository commandeRepository;
    private final ProduitPanierRepository produitPanierRepository;
    private final CommandeMapper commandeMapper;

    @Override
    @Transactional
    public PanierDto creerPanier(PanierRequestDto dto) {

        Panier panier = panierMapper.toEntity(dto);
        panier = panierRepository.save(panier);
        return panierMapper.toDto(panier);
    }

    @Override
    @Transactional
    public PanierDto ajouterProduit(UUID panierId, ProduitPanierRequestDto produitDto) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new ResourceNotFoundException("Panier introuvable"));

        ProduitPanier produitPanier = produitPanierMapper.toEntity(produitDto);
        produitPanier.setPanier(panier);

        produitPanier.setProduitId(produitDto.getProduitId());
        produitPanier.setQuantite(produitDto.getQuantite());

        panier.getProduits().add(produitPanier);
        panierRepository.save(panier);

        return panierMapper.toDto(panier);
    }

    @Override
    public PanierDto mettreAJourQuantite(UUID panierId, UUID produitPanierId, Integer nouvelleQuantite) {

        ProduitPanier produitPanier = produitPanierRepository.findById(produitPanierId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit du panier introuvable"));

        produitPanier.setQuantite(nouvelleQuantite);
        produitPanierRepository.save(produitPanier);

        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new ResourceNotFoundException("Panier introuvable"));

        return panierMapper.toDto(panier);
    }

    @Override
    public PanierDto retirerProduit(UUID panierId, UUID produitPanierId) {

        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new ResourceNotFoundException("Panier introuvable"));

        panier.getProduits().removeIf(p -> p.getId().equals(produitPanierId));
        panierRepository.save(panier);

        return panierMapper.toDto(panier);
    }

    @Override
    public PanierDto trouverParId(UUID id) {

        Panier panier = panierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Panier introuvable"));
        return panierMapper.toDto(panier);
    }

    @Override
    public List<PanierDto> listerPaniersParUtilisateur(UUID utilisateurId) {
        return panierRepository.findByUtilisateurId(utilisateurId).stream()
                .map(panierMapper::toDto)
                .collect(Collectors.toList());
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
    public PanierDto viderPanier(UUID panierId) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new ResourceNotFoundException("Panier introuvable"));

        panier.getProduits().clear();
        panierRepository.save(panier);

        return panierMapper.toDto(panier);
    }

    @Override
    public CommandeDto validerPanier(UUID panierId) {

        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new ResourceNotFoundException("Panier introuvable"));

        List<ProduitCommande> produitsCommande = panier.getProduits().stream()
                .map(pp -> {
                    // Ici tu peux appeler boutique-service pour récupérer le prix du produit
                    BigDecimal prixUnitaire = BigDecimal.valueOf(100); // exemple
                    BigDecimal sousTotal = prixUnitaire.multiply(BigDecimal.valueOf(pp.getQuantite()));

                    return ProduitCommande.builder()
                            .produitId(pp.getProduitId())
                            .quantite(pp.getQuantite())
                            .prixUnitaire(prixUnitaire)
                            .sousTotal(sousTotal)
                            .build();
                })
                .collect(Collectors.toList());

        BigDecimal total =produitsCommande.stream()
                .map(ProduitCommande::getSousTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Commande commande = Commande.builder()
                .utilisateurId(panier.getUtilisateurId())
                .numeroCommande(genererNumeroCommande())
                .statut(StatutCommande.EN_ATTENTE)
                .produits(produitsCommande)
                .montantTotal(total)
                .adresseLivraison("Adresse par défaut") // à récupérer du DTO ou utilisateur-service
                .modeLivraison("STANDARD")
                .build();

        commande = commandeRepository.save(commande);

        // Une fois validé, on peut vider le panier
        panier.getProduits().clear();
        panierRepository.save(panier);

        return commandeMapper.toDto(commande);
    }

    private String genererNumeroCommande() {

        String prefix = "CMD";
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // Compter combien de commandes existent déjà pour aujourd'hui
        LocalDate today = LocalDate.now();
        Date start = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(today.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());

        long count = commandeRepository.countByDate(start, end);

        // Incrémenter le compteur
        long numero = count + 1;

        // Format avec 4 chiffres (0001, 0002…)
        String numeroPart = String.format("%04d", numero);

        return prefix + "-" + datePart + "-" + numeroPart;
    }
}



