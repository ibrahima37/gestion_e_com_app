package maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class SuperAdminDto extends PersonneDto {

    private int niveauAcces;
}