package maboutique.shop.commandeservice.gestionCommande.dtos;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class ProfilDto {

    private UUID id;

    private String code;

    private String libelle;

    private Boolean superAdmin;

    private Set<PermissionDto> permissions = new HashSet<>();

    private Integer nombrePersonnes;
}
