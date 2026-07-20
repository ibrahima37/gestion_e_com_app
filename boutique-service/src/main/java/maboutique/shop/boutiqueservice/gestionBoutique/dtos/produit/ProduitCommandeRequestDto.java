package maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProduitCommandeRequestDto {

    private UUID produitId;

    private Integer quantite;
}