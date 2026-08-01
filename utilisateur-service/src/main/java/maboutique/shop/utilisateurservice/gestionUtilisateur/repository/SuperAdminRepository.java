package maboutique.shop.utilisateurservice.gestionUtilisateur.repository;

import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, UUID> {
}
