package maboutique.shop.boutiqueservice.gestionBoutique.mappers.impl;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.PermissionDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Permission;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.PermissionMapper;
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
