package maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.PermissionDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Permission;

public interface PermissionMapper {

    PermissionDto toDto(Permission permission);
}
