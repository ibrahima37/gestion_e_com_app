package maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReinitialisationMotDePasseDto {

    private String token;

    private String nouveauMotDePasse;

    private String confirmationMotDePasse;

}
