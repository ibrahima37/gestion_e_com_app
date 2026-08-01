package maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class InscriptionDto {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenoms;

    //@ValidEmail
    private String email;

    //@ValidPhone
    private String telephone;

    private String adresse;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Mot de passe trop faible"
    )
    private String motDePasse;
}
