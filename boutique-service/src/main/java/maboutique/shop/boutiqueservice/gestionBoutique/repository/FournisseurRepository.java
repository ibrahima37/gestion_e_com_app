package maboutique.shop.boutiqueservice.gestionBoutique.repository;

import maboutique.shop.boutiqueservice.gestionBoutique.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FournisseurRepository extends JpaRepository<Fournisseur, UUID> {
}
