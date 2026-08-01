package maboutique.shop.commandeservice.gestionCommande.repositories;

import maboutique.shop.commandeservice.gestionCommande.entities.ProduitPanier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProduitPanierRepository extends JpaRepository<ProduitPanier, UUID> {
}
