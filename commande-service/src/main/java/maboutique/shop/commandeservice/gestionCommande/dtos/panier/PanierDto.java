package maboutique.shop.commandeservice.gestionCommande.dtos.panier;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.experimental.SuperBuilder;
import maboutique.shop.commonentities.gestionCommon.dto.BaseEntityDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class PanierDto extends BaseEntityDto {

    private UUID id;

    private UUID userId;

    private String nomUser;

    private BigDecimal montantTotal;

    private List<ProduitPanierDto> produits;

}