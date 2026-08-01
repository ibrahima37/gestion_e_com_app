package maboutique.shop.commandeservice.gestionCommande.dtos.commande;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProduitCommandeRequestDto {

    @NotNull
    private UUID produitId;

    @NotNull
    private Integer quantite;
}