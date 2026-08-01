package maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class UtilisateurDto extends PersonneDto {

    private LocalDate dateNaissance;
    private String preference;
}
