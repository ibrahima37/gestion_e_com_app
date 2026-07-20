package maboutique.shop.boutiqueservice.gestionBoutique.mappers.impl;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.PermissionDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.ProfilDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.UserDetailDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.UserDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.User;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.UserMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User entity) {
        if (entity == null) {
            return null;
        }

        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setPrenoms(entity.getPrenoms());
        dto.setEmail(entity.getEmail());
        dto.setTelephone(entity.getTelephone());
        dto.setAdresse(entity.getAdresse());
        dto.setDateCreation(entity.getDateCreation());
        dto.setDateModification(entity.getDateModification());
        dto.setDateInscription(entity.getDateInscription());
        dto.setCreePar(entity.getCreePar());
        dto.setModifierPar(entity.getModifierPar());
        dto.setStatut(entity.getStatut());

        if (entity.getProfils() != null) {
            dto.setProfils(
                    entity.getProfils().stream()
                            .map(p -> ProfilDto.builder()
                                    .id(p.getId())
                                    .dateCreation(p.getDateCreation())
                                    .dateModification(p.getDateModification())
                                    .creePar(p.getCreePar())
                                    .modifierPar(p.getModifierPar())
                                    .code(p.getCode())
                                    .libelle(p.getLibelle())
                                    .superAdmin(p.getSuperAdmin())
                                    .permissions(
                                            p.getPermissions().stream()
                                                    .map(perm -> PermissionDto.builder()
                                                            .id(perm.getId())
                                                            .nom(perm.getNom())
                                                            .description(perm.getDescription())
                                                            .build()
                                                    )
                                                    .collect(Collectors.toSet())
                                    )
                                    .nombrePersonnes(p.getPersonnes() != null ? p.getPersonnes().size() : 0)
                                    .build()
                            )
                            .collect(Collectors.toSet())
            );
        }
        return dto;
    }

    @Override
    public User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User entity = new User();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setPrenoms(dto.getPrenoms());
        entity.setEmail(dto.getEmail());
        entity.setMotDePasse(dto.getMotDePasse());
        entity.setTelephone(dto.getTelephone());
        entity.setAdresse(dto.getAdresse());
        entity.setDateInscription(dto.getDateInscription());
        entity.setStatut(dto.getStatut());
        return entity;
    }

    @Override
    public UserDetailDto toDetailDto(User entity) {
        if (entity == null) {
            return null;
        }

        UserDetailDto detailDto = new UserDetailDto();
        detailDto.setId(entity.getId());
        detailDto.setNom(entity.getNom());
        detailDto.setPrenoms(entity.getPrenoms());
        detailDto.setEmail(entity.getEmail());
        detailDto.setTelephone(entity.getTelephone());
        detailDto.setAdresse(entity.getAdresse());
        detailDto.setDateInscription(entity.getDateInscription());
        detailDto.setStatut(entity.getStatut());

        if (entity.getProfils() != null) {
            detailDto.setProfils(
                    entity.getProfils().stream()
                            .map(p -> ProfilDto.builder()
                                    .id(p.getId())
                                    .dateCreation(p.getDateCreation())
                                    .dateModification(p.getDateModification())
                                    .creePar(p.getCreePar())
                                    .modifierPar(p.getModifierPar())
                                    .code(p.getCode())
                                    .libelle(p.getLibelle())
                                    .superAdmin(p.getSuperAdmin())
                                    .permissions(
                                            p.getPermissions().stream()
                                                    .map(perm -> PermissionDto.builder()
                                                            .id(perm.getId())
                                                            .nom(perm.getNom())
                                                            .description(perm.getDescription())
                                                            .build()
                                                    )
                                                    .collect(Collectors.toSet())
                                    )
                                    .nombrePersonnes(p.getPersonnes() != null ? p.getPersonnes().size() : 0)
                                    .build()
                            )
                            .collect(Collectors.toSet())
            );
        }
        return detailDto;
    }
}

