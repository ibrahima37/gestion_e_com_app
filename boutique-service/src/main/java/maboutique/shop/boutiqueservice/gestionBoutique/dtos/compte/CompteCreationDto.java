package maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.boutiqueservice.gestionBoutique.utils.ValidEmail;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class CompteCreationDto {

    @ValidEmail
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Mot de passe trop faible"
    )
    private String motDePasse;

    private UUID titulaireId;
}