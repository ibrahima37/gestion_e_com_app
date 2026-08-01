package maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MotDePasseOublieDto {

    @Email
    private String email;
}
