package maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ChangerMotDePasseDto {

    @NotBlank
    private String ancienMotDePasse;

    @NotBlank
    private String nouveauMotDePasse;

    @NotBlank
    private String confirmationMotDePasse;
}