package maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit;

import lombok.*;
import lombok.experimental.SuperBuilder;
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
public class ProduitResumeDto extends BaseEntityDto {

    private UUID id;

    private String nomProduit;

    private String marque;

    private BigDecimal prix;

    private List<String> images;

    private float notation;

    private int nombreAvis;

    private UUID categorieId;

    private String nomCategorie;
}