package maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.impl;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.AdminDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Admin;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.AdminMapper;
import org.springframework.stereotype.Component;

@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public AdminDto toDto(Admin entity) {

        if (entity == null) return null;

        return AdminDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenoms(entity.getPrenoms())
                .email(entity.getEmail())
                .telephone(entity.getTelephone())
                .adresse(entity.getAdresse())
                .departement(entity.getDepartement())
                .build();
    }

    @Override
    public Admin toEntity(AdminDto dto) {

        if (dto == null) return null;

        Admin admin = new Admin();
        admin.setId(dto.getId());
        admin.setNom(dto.getNom());
        admin.setPrenoms(dto.getPrenoms());
        admin.setEmail(dto.getEmail());
        admin.setTelephone(dto.getTelephone());
        admin.setAdresse(dto.getAdresse());
        admin.setDepartement(dto.getDepartement());
        return admin;
    }
}
