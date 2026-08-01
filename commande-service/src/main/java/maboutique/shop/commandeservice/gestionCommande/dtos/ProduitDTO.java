package maboutique.shop.commandeservice.gestionCommande.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.commonentities.gestionCommon.dto.BaseEntityDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ProduitDTO extends BaseEntityDto {

    private UUID id;
    private String nomProduit;
    private String marque;
    private String modele;
    private String code;
    private BigDecimal prixAchat;
    private BigDecimal prixVente;
    private Integer stock;
    private String description;
    private String specification;
    private List<String> images;
    private BigDecimal notation;
    private int nombreAvis;
    private UUID categorieId;
    private String nomCategorie;
}
