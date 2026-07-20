package maboutique.shop.boutiqueservice.gestionBoutique.repository;

import maboutique.shop.boutiqueservice.gestionBoutique.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategorieRepository extends JpaRepository<Categorie, UUID> {

    Optional<Categorie> findByNomCategorie(String nomCategorie);

    boolean existsByNomCategorieIgnoreCase(String nomCategorie);
}
