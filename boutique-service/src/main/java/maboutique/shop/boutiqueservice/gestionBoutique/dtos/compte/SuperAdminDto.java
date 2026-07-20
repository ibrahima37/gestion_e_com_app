package maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte;

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