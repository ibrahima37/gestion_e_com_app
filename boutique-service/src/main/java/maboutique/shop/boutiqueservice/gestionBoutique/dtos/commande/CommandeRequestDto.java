package maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitCommandeRequestDto;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CommandeRequestDto {

    @NotNull
    private UUID userId;

    @NotEmpty
    private List<ProduitCommandeRequestDto> produits;

    @NotBlank
    private String adresseLivraison;

    @NotBlank
    private String modeLivraison;
}
