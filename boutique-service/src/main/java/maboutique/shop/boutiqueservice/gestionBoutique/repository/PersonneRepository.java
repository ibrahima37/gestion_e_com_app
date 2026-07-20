package maboutique.shop.boutiqueservice.gestionBoutique.repository;

import maboutique.shop.boutiqueservice.gestionBoutique.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonneRepository
        extends JpaRepository<Personne, UUID> {

    List<Personne> findByStatutTrue();

    List<Personne> findByStatutFalse();

    Optional<Personne> findByEmail(String email);
}
