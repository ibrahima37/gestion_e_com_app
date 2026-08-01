package maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.impl;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.PermissionDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Permission;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.PermissionMapper;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public PermissionDto toDto(Permission permission){

        return PermissionDto.builder()
                .id(permission.getId())
                .nom(permission.getNom())
                .description(permission.getDescription())
                .actif(permission.getActif())
                .build();
    }
}
