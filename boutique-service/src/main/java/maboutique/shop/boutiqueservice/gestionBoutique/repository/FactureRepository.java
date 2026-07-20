package maboutique.shop.boutiqueservice.gestionBoutique.repository;

import maboutique.shop.boutiqueservice.gestionBoutique.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FactureRepository extends JpaRepository<Facture, UUID> {
}
