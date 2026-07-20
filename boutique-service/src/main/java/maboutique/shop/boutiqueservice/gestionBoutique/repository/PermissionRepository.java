package maboutique.shop.boutiqueservice.gestionBoutique.repository;

import maboutique.shop.boutiqueservice.gestionBoutique.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository
        extends JpaRepository<Permission, UUID> {


    List<Permission> findByActifTrue();


    List<Permission> findByNomContainingIgnoreCase(String nom);

    Optional<Permission> findByNom(String nom);
}