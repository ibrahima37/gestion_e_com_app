package maboutique.shop.boutiqueservice.gestionBoutique.services.impl;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.FournisseurDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Fournisseur;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.FournisseurRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.achat.AchatDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.achat.AchatRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Achat;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Produit;
import maboutique.shop.boutiqueservice.gestionBoutique.exceptions.ResourceNotFoundException;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.AchatMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.AchatRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.ProduitRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.AchatService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AchatServiceImpl implements AchatService {

    private final AchatRepository achatRepository;
    private final ProduitRepository produitRepository;
    private final FournisseurRepository fournisseurRepository;
    private final AchatMapper achatMapper;

    @Override
    public AchatDto creerAchat(AchatRequestDto dto, UUID fournisseurId) {

        Produit produit = produitRepository.findById(dto.getProduitId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produit introuvable."));

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurId);
        fournisseur.setNom(fournisseur.getNom());

        Achat achat = achatMapper.toEntity(dto);

        achat.setProduit(produit);
        achat.setFournisseur(fournisseur);

        achatRepository.save(achat);

        produit.setStock(
                produit.getStock() + dto.getQuantiteAchetee());

        produitRepository.save(produit);

        return achatMapper.toDto(achat);
    }

    @Override
    public AchatDto modifierAchat(UUID id, AchatRequestDto dto) {

        Achat achat = achatRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Achat introuvable."));

        Produit produit = produitRepository.findById(dto.getProduitId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produit introuvable."));

        /*
         * On retire l'ancienne quantité du stock
         */
        Produit ancienProduit = achat.getProduit();

        ancienProduit.setStock(
                ancienProduit.getStock()
                        - achat.getQuantiteAchetee());

        /*
         * On ajoute la nouvelle quantité
         */
        produit.setStock(
                produit.getStock()
                        + dto.getQuantiteAchetee());

        achat.setProduit(produit);
        achat.setQuantiteAchetee(dto.getQuantiteAchetee());
        achat.setPrixAchatUnitaire(dto.getPrixAchatUnitaire());

        produitRepository.save(ancienProduit);

        if (!ancienProduit.getId().equals(produit.getId())) {
            produitRepository.save(produit);
        }

        Achat saved = achatRepository.save(achat);

        return achatMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AchatDto trouverParId(UUID id) {

        Achat achat = achatRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Achat introuvable."));

        return achatMapper.toDto(achat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AchatDto> trouverTous() {

        return achatMapper.toDto(achatRepository.findAll());
    }

    @Override
    public void supprimer(UUID id) {

        Achat achat = achatRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Achat introuvable."));

        Produit produit = achat.getProduit();

        produit.setStock(
                produit.getStock()
                        - achat.getQuantiteAchetee());

        produitRepository.save(produit);

        achatRepository.delete(achat);
    }
}
