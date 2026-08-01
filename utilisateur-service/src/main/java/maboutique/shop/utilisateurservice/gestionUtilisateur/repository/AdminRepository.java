package maboutique.shop.utilisateurservice.gestionUtilisateur.repository;

import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
}
