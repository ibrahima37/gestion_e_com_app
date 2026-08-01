package maboutique.shop.paiementservice.gestionPaiement.services.ints;

import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.FactureDto;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutFacture;

import java.util.List;
import java.util.UUID;

public interface FactureService {

    // Générer une facture à partir d'une commande
    FactureDto genererFacture(UUID commandeId);

    // Consulter une facture
    FactureDto obtenirDetails(UUID factureId);

    // Télécharger la facture (PDF)
    byte[] telechargerFacture(UUID factureId);

    // Envoyer la facture par e-mail
    void envoyerFacture(UUID factureId, String email);

    // Modifier une facture (si autorisé)
   // FactureDto modifierFacture(UUID factureId, FactureUpdateDto dto);

    // Valider une facture
    void validerFacture(UUID factureId);

    // Imprimer une facture
    byte[] imprimerFacture(UUID factureId);

    // Liste des factures
    List<FactureDto> listerFactures();

    // Supprimer une facture (optionnel)
    void supprimerFacture(UUID factureId);

    FactureDto trouverParId(UUID factureId);

    List<FactureDto> trouverParClient(UUID clientId);

    List<FactureDto> trouverParCommande(UUID commandeId);

    List<FactureDto> trouverParStatut(StatutFacture statut);
}
