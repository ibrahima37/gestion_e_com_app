package maboutique.shop.utilisateurservice.gestionUtilisateur.repository;

import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID> {
}
