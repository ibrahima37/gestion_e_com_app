package maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ConnexionDto {

    private String email;
    private String motDePasse;
}
