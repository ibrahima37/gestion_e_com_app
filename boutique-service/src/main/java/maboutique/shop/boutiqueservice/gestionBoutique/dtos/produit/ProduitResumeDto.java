package maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.commonentities.gestionCommon.dto.BaseEntityDto;

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
    private BigDecimal prixVente;
    private List<String> images;
    private BigDecimal notation;
    private int nombreAvis;

    private UUID categorieId;
    private String nomCategorie;
}