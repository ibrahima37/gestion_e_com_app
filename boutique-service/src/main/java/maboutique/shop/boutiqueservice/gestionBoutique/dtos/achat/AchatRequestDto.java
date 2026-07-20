package maboutique.shop.boutiqueservice.gestionBoutique.dtos.achat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AchatRequestDto {

    @NotNull
    private UUID produitId;

    @NotNull
    private UUID fournisseurId;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 1, message = "La quantité doit être au minimum 1")
    private Integer quantiteAchetee;

    @NotNull(message = "Le prix d'achat est obligatoire")
    @Positive(message = "Le prix doit être supérieur à 0")
    private BigDecimal prixAchatUnitaire;
}
