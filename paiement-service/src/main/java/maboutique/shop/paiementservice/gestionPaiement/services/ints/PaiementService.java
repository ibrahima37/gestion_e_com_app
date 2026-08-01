package maboutique.shop.paiementservice.gestionPaiement.services.ints;

import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.PaiementDto;
import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.PaiementRequestDto;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutPaiement;

import java.util.List;
import java.util.UUID;

public interface PaiementService {

    PaiementDto effectuerPaiement(PaiementRequestDto dto);

    List<PaiementDto> listerPaiements();

    List<PaiementDto> listerParCommande(UUID commandeId);

    List<PaiementDto> listerParStatut(StatutPaiement statut);

    List<PaiementDto> findPaiementsByUtilisateur(UUID utilisateurId);
}
