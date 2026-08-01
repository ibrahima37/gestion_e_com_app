package maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.impl;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.UtilisateurDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Utilisateur;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.UtilisateurMapper;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapperImpl implements UtilisateurMapper {

    @Override
    public UtilisateurDto toDto(Utilisateur entity) {
        if (entity == null) return null;

        return UtilisateurDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenoms(entity.getPrenoms())
                .email(entity.getEmail())
                .telephone(entity.getTelephone())
                .adresse(entity.getAdresse())
                .dateNaissance(entity.getDateNaissance())
                .preference(entity.getPreference())
                .build();
    }

    @Override
    public Utilisateur toEntity(UtilisateurDto dto) {
        if (dto == null) return null;

        Utilisateur user = new Utilisateur();
        user.setId(dto.getId());
        user.setNom(dto.getNom());
        user.setPrenoms(dto.getPrenoms());
        user.setEmail(dto.getEmail());
        user.setTelephone(dto.getTelephone());
        user.setAdresse(dto.getAdresse());
        user.setDateNaissance(dto.getDateNaissance());
        user.setPreference(dto.getPreference());
        return user;
    }
}

