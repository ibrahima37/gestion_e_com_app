package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.PermissionDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Permission;

public interface PermissionMapper {

    PermissionDto toDto(Permission permission);
}
