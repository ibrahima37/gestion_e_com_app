package maboutique.shop.boutiqueservice.gestionBoutique.dtos.achat;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.BaseEntityDto;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class AchatDto extends BaseEntityDto {

    private UUID id;

    private UUID produitId;

    private String nomProduit;

    private UUID fournisseurId;

    private String nomFournisseur;

    private Integer quantiteAchetee;

    private BigDecimal prixAchatUnitaire;

    private BigDecimal montantTotal;
}
