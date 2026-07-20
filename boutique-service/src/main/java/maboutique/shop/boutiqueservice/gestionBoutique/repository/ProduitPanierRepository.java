package maboutique.shop.boutiqueservice.gestionBoutique.repository;

import maboutique.shop.boutiqueservice.gestionBoutique.entities.ProduitPanier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProduitPanierRepository extends JpaRepository<ProduitPanier, UUID> {
}
