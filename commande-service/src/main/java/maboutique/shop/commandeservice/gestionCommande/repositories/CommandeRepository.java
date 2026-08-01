package maboutique.shop.commandeservice.gestionCommande.repositories;

import maboutique.shop.commandeservice.gestionCommande.entities.Commande;
import maboutique.shop.commandeservice.gestionCommande.enums.StatutCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface CommandeRepository extends JpaRepository<Commande, UUID> {

    List<Commande> findByUserId(UUID userId);

    @Query("""
       SELECT COALESCE(SUM(pc.quantite), 0)
       FROM ProduitCommande pc
       WHERE pc.produit.categories.id = :categorieId
        AND MONTH(pc.commande.dateCreation) = :mois
        AND YEAR(pc.commande.dateCreation) = :annee
    """)
    Integer compterProduitsVendus(
            @Param("categorieId") UUID categorieId,
            @Param("mois") int mois,
            @Param("annee") int annee
    );


    @Query("""
      SELECT COALESCE(SUM(pc.sousTotal), 0)
      FROM ProduitCommande pc
      WHERE pc.produit.categories.id = :categorieId
        AND MONTH(pc.commande.dateCreation) = :mois
        AND YEAR(pc.commande.dateCreation) = :annee
    """)
    BigDecimal calculerChiffreAffaires(
            @Param("categorieId") UUID categorieId,
            @Param("mois") int mois,
            @Param("annee") int annee
    );

    @Query("""
        SELECT COALESCE(
            SUM(
                (pc.prixUnitaire - pc.prixAchatUnitaire)
                * pc.quantite
            ),
            0
        )
        FROM ProduitCommande pc
        WHERE MONTH(pc.commande.dateCreation) = :mois
            AND YEAR(pc.commande.dateCreation) = :annee
    """)
    BigDecimal calculerBeneficeMensuel(
            @Param("mois") int mois,
            @Param("annee") int annee
    );

    @Query("SELECT COUNT(c) FROM Commande c WHERE c.dateCreation BETWEEN :start AND :end")
    long countByDate(@Param("start") Date start, @Param("end") Date end);

    // Trouver toutes les commandes par statut
    List<Commande> findByStatut(StatutCommande statut);

    // Trouver toutes les commandes par utilisateur
    List<Commande> findByUtilisateurId(UUID utilisateurId);
}
