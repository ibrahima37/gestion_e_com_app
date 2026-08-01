package maboutique.shop.utilisateurservice.gestionUtilisateur.repository;

import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Profil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfilRepository extends JpaRepository<Profil, UUID> {

    List<Profil> findBySuperAdminFalse();

    Optional<Profil> findByCode(String code);
    Optional<Profil> findByLibelle(String libelle);

    Boolean existsByCode(String code);
    Boolean existsByLibelle(String libelle);

}
