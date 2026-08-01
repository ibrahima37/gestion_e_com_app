package maboutique.shop.commandeservice.gestionCommande.dtos.commande;

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
@ToString(callSuper = true)
@SuperBuilder
public class ProduitCommandeDto extends BaseEntityDto {

    private UUID id;
    private UUID commandeId;
    private UUID produitId;
    private String nomProduit;
    private Integer quantite;
    private List<String> images;
    private BigDecimal prixUnitaire;
    private BigDecimal prixAchatUnitaire;
    private BigDecimal sousTotal;

}