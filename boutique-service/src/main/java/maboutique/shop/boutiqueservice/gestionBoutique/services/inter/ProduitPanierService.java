package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitPanierDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProduitPanierService {

    // Ajouter un produit au panier
    ProduitPanierDto ajouterProduit(UUID panierId, UUID produitId, int quantite);

    // Modifier la quantité d'un produit
    ProduitPanierDto modifierQuantite(UUID panierId, UUID produitId, int quantite);

    // Augmenter la quantité
    ProduitPanierDto augmenterQuantite(UUID panierId, UUID produitId);

    // Diminuer la quantité
    ProduitPanierDto diminuerQuantite(UUID panierId, UUID produitId);

    // Supprimer un produit du panier
    void supprimerProduit(UUID panierId, UUID produitId);

    // Rechercher une ligne du panier
    ProduitPanierDto trouverParId(UUID produitPanierId);

    // Lister les produits d'un panier
    List<ProduitPanierDto> listerParPanier(UUID panierId);

    // Vérifier si un produit est déjà dans le panier
    boolean existeDansPanier(UUID panierId, UUID produitId);

    // Calculer le sous-total d'une ligne
    BigDecimal calculerSousTotal(UUID produitPanierId);
}
