package maboutique.shop.commandeservice.gestionCommande.repositories;

import maboutique.shop.commandeservice.gestionCommande.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PanierRepository extends JpaRepository<Panier, UUID> {

    // Recherche tous les paniers d’un utilisateur donné
    List<Panier> findByUtilisateurId(UUID utilisateurId);
}
