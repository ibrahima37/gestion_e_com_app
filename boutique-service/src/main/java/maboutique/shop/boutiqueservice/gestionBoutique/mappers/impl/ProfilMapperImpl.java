package maboutique.shop.boutiqueservice.gestionBoutique.mappers.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.ProfilCreationDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.ProfilDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Profil;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.PermissionMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.ProfilMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProfilMapperImpl implements ProfilMapper {

    private final PermissionMapper permissionMapper;

    @Override
    public Profil toEntity(ProfilCreationDto dto) {

        if (dto == null) {
            return null;
        }

        return Profil.builder()
                .code(dto.getCode())
                .libelle(dto.getLibelle())
                .superAdmin(dto.getSuperAdmin())
                .build();
    }

    @Override
    public ProfilDto toDto(Profil profil){

        if(profil == null){
            return null;
        }

        return ProfilDto.builder()
                .id(profil.getId())
                .code(profil.getCode())
                .libelle(profil.getLibelle())
                .superAdmin(profil.getSuperAdmin())

                .permissions(
                        profil.getPermissions()
                                .stream()
                                .map(permissionMapper::toDto)
                                .collect(Collectors.toSet())
                )

                .nombrePersonnes(
                        profil.getPersonnes()!=null
                                ? profil.getPersonnes().size()
                                : 0
                )

                .build();
    }

    @Override
    public Profil toEntity(ProfilDto dto) {

        if (dto == null) {
            return null;
        }

        return Profil.builder()
                .id(dto.getId())
                .code(dto.getCode())
                .libelle(dto.getLibelle())
                .superAdmin(dto.getSuperAdmin())
                .build();
    }
}
