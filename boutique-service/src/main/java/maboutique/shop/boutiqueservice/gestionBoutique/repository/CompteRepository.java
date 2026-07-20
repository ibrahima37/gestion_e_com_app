package maboutique.shop.boutiqueservice.gestionBoutique.repository;

import maboutique.shop.boutiqueservice.gestionBoutique.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompteRepository extends JpaRepository<Compte, UUID> {

    Optional<Compte> findByTitulaireId(UUID titulaireId);

    @Query("select lower(c.email) from Compte c")
    List<String> findAllEmails();

    Optional<Compte> findByTitulaireEmail(String email);

    Optional<Compte> findByEmail(String email);
}
