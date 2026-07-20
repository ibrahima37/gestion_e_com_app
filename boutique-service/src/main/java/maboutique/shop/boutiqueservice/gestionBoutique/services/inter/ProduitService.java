package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitRequestDto;

import java.util.List;
import java.util.UUID;

public interface ProduitService {

    // Création
    ProduitDto creerProduit(ProduitRequestDto dto);

    // Modification
    //ProduitDto modifierProduit(UUID id, ProduitModificationDto dto);

    // Suppression
    void supprimerProduit(UUID id);

    // Consultation
    ProduitDto trouverParId(UUID id);

    List<ProduitDto> listerProduits();

    // Recherche
    List<ProduitDto> rechercherParNom(String nom);

    List<ProduitDto> rechercherParCategorie(UUID categorieId);


    // Stock
    void corrigerStock(UUID produitId, int nouvelleQuantite);

//    void mettreAJourStock(UUID produitId, int quantite);
//
//    void augmenterStock(UUID produitId, int quantite);
//
//    void diminuerStock(UUID produitId, int quantite);

    // Disponibilité
    boolean estDisponible(UUID produitId);

    // Produits en promotion (optionnel)
    List<ProduitDto> listerProduitsEnPromotion();
}
