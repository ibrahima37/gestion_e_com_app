package maboutique.shop.paiementservice.gestionPaiement.repository;

import maboutique.shop.paiementservice.gestionPaiement.entities.Paiement;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutPaiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface PaiementRepository extends JpaRepository<Paiement, UUID> {

    List<Paiement> findByCommandeId(UUID commandeId);

    List<Paiement> findByStatut(StatutPaiement statut);

    List<Paiement> findByClientId(UUID clientId);
}
