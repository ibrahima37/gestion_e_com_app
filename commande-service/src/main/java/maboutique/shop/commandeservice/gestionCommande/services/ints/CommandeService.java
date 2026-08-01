package maboutique.shop.commandeservice.gestionCommande.services.ints;

import maboutique.shop.commandeservice.gestionCommande.dtos.commande.CommandeDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.commande.CommandeRequestDto;
import maboutique.shop.commandeservice.gestionCommande.enums.StatutCommande;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CommandeService {

    // Créer une nouvelle commande à partir d’un DTO de requête
    CommandeDto creerCommande(CommandeRequestDto dto);

    // Récupérer une commande par son identifiant
    CommandeDto trouverParId(UUID id);

    // Lister toutes les commandes d’un utilisateur
    List<CommandeDto> listerCommandesParUtilisateur(UUID utilisateurId);

    // Lister toutes les commandes (admin)
    List<CommandeDto> listerToutesCommandes();

    // Changer le statut d’une commande (ex: EN_ATTENTE -> EN_COURS)
    CommandeDto changerStatut(UUID id, StatutCommande nouveauStatut);

    // Annuler une commande si elle est encore annulable
    CommandeDto annulerCommande(UUID id);

    // Mettre à jour l’adresse ou le mode de livraison
    CommandeDto mettreAJourLivraison(UUID id, String nouvelleAdresse, String nouveauMode);

    // Calculer et mettre à jour le montant total d’une commande
    CommandeDto recalculerMontant(UUID id);

    // Lister les commandes par statut (utile pour suivi logistique)
    List<CommandeDto> listerCommandesParStatut(StatutCommande statut);

    // Supprimer définitivement une commande (cas admin)
    void supprimerCommande(UUID id);

    BigDecimal calculerChiffreAffairesParCategorie(UUID categorieId, int mois, int annee);
}

