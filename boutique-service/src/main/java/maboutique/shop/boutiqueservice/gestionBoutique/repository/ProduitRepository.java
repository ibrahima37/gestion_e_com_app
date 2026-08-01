package maboutique.shop.boutiqueservice.gestionBoutique.repository;

import maboutique.shop.boutiqueservice.gestionBoutique.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProduitRepository extends JpaRepository<Produit, UUID> {

    Integer countByCategoriesId(UUID categorieId);

    Integer countByStockGreaterThan(int stock);

    Optional<Produit> findByNomProduitIgnoreCase(String nomProduit);

    boolean existsByNomProduitIgnoreCase(String nomProduit);

    List<Produit> findByNomProduitContainingIgnoreCase(String nomProduit);

    List<Produit> findByCategories_Id(UUID categorieId);
}
