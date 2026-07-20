package maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProduitPanierRequestDto {

    private UUID produitId;

    private Integer quantite;
}
