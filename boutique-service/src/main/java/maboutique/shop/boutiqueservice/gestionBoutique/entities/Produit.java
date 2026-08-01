package maboutique.shop.boutiqueservice.gestionBoutique.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.commonentities.gestionCommon.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString(exclude = "categories")
@Entity
@Table(name = "produits")
public class Produit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nomProduit;

    private String marque;

    private String modele;

    private String code;

    @Column(precision = 10, scale = 2)
    private BigDecimal prixAchat;

    @Column(precision = 10, scale = 2)
    private BigDecimal prixVente;

    @Builder.Default
    private Integer stock = 0;

    private String description;
    private String specification;

    @Builder.Default
    @ElementCollection
    @CollectionTable(
            name = "produit_images",
            joinColumns = @JoinColumn(name = "produit_id"))
    @Column(name = "image")
    private List<String> images = new ArrayList<>();

    @Column(nullable = false, precision = 2, scale = 1)
    @Builder.Default
    private BigDecimal notation = BigDecimal.ZERO;

    @Column(nullable = false)
    @Builder.Default
    private int nombreAvis = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id")
    private Categorie categories;
}
