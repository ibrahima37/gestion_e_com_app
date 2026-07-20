package maboutique.shop.boutiqueservice.gestionBoutique.services.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.exceptions.DuplicateResourceException;
import maboutique.shop.boutiqueservice.gestionBoutique.exceptions.ResourceNotFoundException;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.ProduitMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.CategorieRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.ProduitRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.ProduitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Categorie;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Produit;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final ProduitMapper produitMapper;

    @Override
    public ProduitDto creerProduit(ProduitRequestDto dto) {

        if (produitRepository.existsByNomProduitIgnoreCase(dto.getNomProduit())) {
            throw new DuplicateResourceException(
                    "Le produit " + dto.getNomProduit() + " existe déjà.");
        }

        Categorie categorie = categorieRepository.findById(dto.getCategorieId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Catégorie introuvable."));

        Produit produit = produitMapper.toEntity(dto);

        produit.setCategories(categorie);

        produit = produitRepository.save(produit);

        return produitMapper.toDto(produit);
    }

    @Override
    public void supprimerProduit(UUID id) {

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produit introuvable."));

        produitRepository.delete(produit);
    }

    @Override
    @Transactional(readOnly = true)
    public ProduitDto trouverParId(UUID id) {

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produit introuvable."));

        return produitMapper.toDto(produit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduitDto> listerProduits() {

        return produitRepository.findAll()
                .stream()
                .map(produitMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduitDto> rechercherParNom(String nom) {

        return produitRepository.findByNomProduitContainingIgnoreCase(nom)
                .stream()
                .map(produitMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduitDto> rechercherParCategorie(UUID categorieId) {

        return produitRepository.findByCategories_Id(categorieId)
                .stream()
                .map(produitMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public void corrigerStock(UUID produitId, int nouvelleQuantite) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit introuvable"));

        produit.setStock(nouvelleQuantite);
        produitRepository.save(produit);
    }

//    @Override
//    public void mettreAJourStock(UUID produitId, int quantite) {
//
//        Produit produit = produitRepository.findById(produitId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Produit introuvable."));
//
//        produit.setStock(quantite);
//
//        produitRepository.save(produit);
//    }
//
//    @Override
//    public void augmenterStock(UUID produitId, int quantite) {
//
//        Produit produit = produitRepository.findById(produitId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Produit introuvable."));
//
//        produit.setStock(produit.getStock() + quantite);
//
//        produitRepository.save(produit);
//    }
//
//    @Override
//    public void diminuerStock(UUID produitId, int quantite) {
//
//        Produit produit = produitRepository.findById(produitId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Produit introuvable."));
//
//        if (produit.getStock() < quantite) {
//            throw new IllegalArgumentException("Stock insuffisant.");
//        }
//
//        produit.setStock(produit.getStock() - quantite);
//
//        produitRepository.save(produit);
//    }

    @Override
    @Transactional(readOnly = true)
    public boolean estDisponible(UUID produitId) {

        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produit introuvable."));

        return produit.getStock() > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduitDto> listerProduitsEnPromotion() {

        // A adapter lorsque tu ajouteras une entité Promotion
        return List.of();
    }
}
