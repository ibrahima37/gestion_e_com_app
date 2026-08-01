package maboutique.shop.paiementservice.gestionPaiement.repository;

import maboutique.shop.paiementservice.gestionPaiement.entities.Facture;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutFacture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FactureRepository extends JpaRepository<Facture, UUID> {

    List<Facture> findByClientId(UUID clientId);

    List<Facture> findByCommandeId(UUID commandeId);

    List<Facture> findByStatut(StatutFacture statut);
}
