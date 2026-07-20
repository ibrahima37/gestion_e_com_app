package maboutique.shop.boutiqueservice.gestionBoutique.dtos.panier;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.experimental.SuperBuilder;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.BaseEntityDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitPanierDto;

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