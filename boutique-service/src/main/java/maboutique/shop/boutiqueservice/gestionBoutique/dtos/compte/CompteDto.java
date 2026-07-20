package maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class CompteDto extends BaseEntityDto {

    private UUID id;

    private String email;

    private Boolean isPasswordReset;

    private Boolean isActived;

    private UUID titulaireId;

    private Date premiereConnexion;

    private Date derniereConnexion;

    private Date derniereDeconnexion;
}
