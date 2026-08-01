package maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.commonentities.gestionCommon.dto.BaseEntityDto;

import java.time.LocalDateTime;
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

    private LocalDateTime premiereConnexion;
    private LocalDateTime derniereConnexion;
    private LocalDateTime derniereDeconnexion;
}
