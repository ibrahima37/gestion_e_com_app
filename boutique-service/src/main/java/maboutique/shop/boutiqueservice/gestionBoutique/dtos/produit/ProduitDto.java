package maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.avis.AvisDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.BaseEntityDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class ProduitDto extends BaseEntityDto {

    private UUID id;

    private String nomProduit;

    private String marque;

    private String modele;

    private String code;

    private BigDecimal prix;

    private int stock;

    private String description;

    private String specification;

    private List<String> images;

    private float notation;

    private int nombreAvis;

    // Relation avec la catégorie
    private UUID categorieId;

    // Liste des avis du produit
    private List<AvisDto> avis;
}