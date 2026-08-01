package maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.impl;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.SuperAdminDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.SuperAdmin;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.SuperAdminMapper;
import org.springframework.stereotype.Component;

@Component
public class SuperAdminMapperImpl implements SuperAdminMapper {

    @Override
    public SuperAdminDto toDto(SuperAdmin entity) {
        if (entity == null) return null;

        return SuperAdminDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenoms(entity.getPrenoms())
                .email(entity.getEmail())
                .telephone(entity.getTelephone())
                .adresse(entity.getAdresse())
                .niveauAcces(entity.getNiveauAcces())
                .build();
    }

    @Override
    public SuperAdmin toEntity(SuperAdminDto dto) {
        if (dto == null) return null;

        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setId(dto.getId());
        superAdmin.setNom(dto.getNom());
        superAdmin.setPrenoms(dto.getPrenoms());
        superAdmin.setEmail(dto.getEmail());
        superAdmin.setTelephone(dto.getTelephone());
        superAdmin.setAdresse(dto.getAdresse());
        superAdmin.setNiveauAcces(dto.getNiveauAcces());
        return superAdmin;
    }
}

