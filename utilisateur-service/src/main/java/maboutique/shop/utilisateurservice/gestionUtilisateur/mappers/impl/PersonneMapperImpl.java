package maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.PersonneDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.ProfilDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Personne;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.PersonneMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PersonneMapperImpl implements PersonneMapper {

    @Override
    public PersonneDto toDto(Personne entity) {

        if (entity == null) return null;

        return PersonneDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenoms(entity.getPrenoms())
                .email(entity.getEmail())
                .telephone(entity.getTelephone())
                .adresse(entity.getAdresse())
                .profils(entity.getProfils()
                        .stream()
                        .map(profil -> ProfilDto.builder()
                                .id(profil.getId())
                                .code(profil.getCode())
                                .libelle(profil.getLibelle())
                                .superAdmin(profil.getSuperAdmin())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Personne toEntity(PersonneDto dto) {
        if (dto == null) {
            return null;
        }

        Personne personne = new Personne();
        personne.setId(dto.getId());
        personne.setNom(dto.getNom());
        personne.setPrenoms(dto.getPrenoms());
        personne.setEmail(dto.getEmail());
        personne.setTelephone(dto.getTelephone());
        personne.setAdresse(dto.getAdresse());

        return personne;
    }
}
