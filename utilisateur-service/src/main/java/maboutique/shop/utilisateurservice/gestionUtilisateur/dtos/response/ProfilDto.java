package maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.commonentities.gestionCommon.dto.BaseEntityDto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class ProfilDto extends BaseEntityDto {

    private UUID id;

    private String code;

    private String libelle;

    private Boolean superAdmin;

    private Set<PermissionDto> permissions = new HashSet<>();

    private Integer nombrePersonnes;
}
