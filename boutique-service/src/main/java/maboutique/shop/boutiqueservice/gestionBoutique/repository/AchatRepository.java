package maboutique.shop.boutiqueservice.gestionBoutique.repository;

import maboutique.shop.boutiqueservice.gestionBoutique.entities.Achat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface AchatRepository extends JpaRepository<Achat, UUID> {

    @Query("""
        SELECT COALESCE(SUM(a.quantiteAchetee * a.prixAchatUnitaire), 0)
        FROM Achat a
        WHERE a.produit.categories.id = :categorieId
        AND MONTH(a.dateCreation) = :mois
        AND YEAR(a.dateCreation) = :annee
    """)
    BigDecimal calculerTotalAchatsCategorie(
            @Param("categorieId") UUID categorieId,
            @Param("mois") int mois,
            @Param("annee") int annee
    );


    @Query("""
        SELECT COALESCE(SUM(a.quantiteAchetee), 0)
        FROM Achat a
        WHERE a.produit.categories.id = :categorieId
    """)
    Integer compterQuantiteAchetee(
            @Param("categorieId") UUID categorieId
    );
}
