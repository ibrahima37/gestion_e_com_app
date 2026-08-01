package maboutique.shop.utilisateurservice.gestionUtilisateur.repository;

import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Personne;
import maboutique.shop.commonsecurity.gestionSecurity.interfaces.IPersonnes;
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
