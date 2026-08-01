package maboutique.shop.commandeservice.gestionCommande.dtos.panier;

import lombok.*;

import java.util.List;
import java.util.UUID;

import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class ProduitPanierDto {

    private UUID id;

    private UUID panierId;

    private UUID produitId;

    private String nomProduit;

    private List<String> images;

    private Integer quantite;

}