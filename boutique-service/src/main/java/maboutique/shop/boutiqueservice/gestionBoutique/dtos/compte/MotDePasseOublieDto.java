package maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte;

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
