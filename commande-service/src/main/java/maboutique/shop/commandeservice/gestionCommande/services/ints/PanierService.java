package maboutique.shop.commandeservice.gestionCommande.services.ints;

import maboutique.shop.commandeservice.gestionCommande.dtos.commande.CommandeDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.PanierDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.PanierRequestDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.ProduitPanierRequestDto;

import java.util.List;
import java.util.UUID;

public interface PanierService {

    // Créer un nouveau panier pour un utilisateur
    PanierDto creerPanier(PanierRequestDto dto);

    // Ajouter un produit dans un panier existant
    PanierDto ajouterProduit(UUID panierId, ProduitPanierRequestDto produitDto);

    // Mettre à jour la quantité d’un produit dans le panier
    PanierDto mettreAJourQuantite(UUID panierId, UUID produitPanierId, Integer nouvelleQuantite);

    // Retirer un produit du panier
    PanierDto retirerProduit(UUID panierId, UUID produitPanierId);

    // Récupérer un panier par son identifiant
    PanierDto trouverParId(UUID id);

    // Lister tous les paniers d’un utilisateur
    List<PanierDto> listerPaniersParUtilisateur(UUID utilisateurId);

    // Supprimer un panier
    void supprimerPanier(UUID id);

    // Vider complètement un panier
    PanierDto viderPanier(UUID panierId);

    // Valider un panier et le transformer en commande
    CommandeDto validerPanier(UUID panierId);
}

