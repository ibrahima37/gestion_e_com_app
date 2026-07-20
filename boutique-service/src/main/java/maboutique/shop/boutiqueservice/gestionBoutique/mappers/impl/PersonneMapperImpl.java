package maboutique.shop.boutiqueservice.gestionBoutique.mappers.impl;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.PersonneDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.UserDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Personne;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.PersonneMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonneMapperImpl implements PersonneMapper {

    @Override
    public PersonneDto toDto(Personne entity) {
        if (entity == null) {
            return null;
        }

        return PersonneDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenoms(entity.getPrenoms())
                .email(entity.getEmail())
                .telephone(entity.getTelephone())
                .adresse(entity.getAdresse())
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

    @Override
    public UserDto toUserDto(Personne entity) {
        if (entity == null) {
            return null;
        }

        return UserDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenoms(entity.getPrenoms())
                .email(entity.getEmail())
                .telephone(entity.getTelephone())
                .adresse(entity.getAdresse())
                .build();
    }

    @Override
    public void updateEntityFromDto(UserDto dto, Personne personne) {
        if (dto == null || personne == null) {
            return;
        }

        personne.setNom(dto.getNom());
        personne.setPrenoms(dto.getPrenoms());
        personne.setEmail(dto.getEmail());
        personne.setTelephone(dto.getTelephone());
        personne.setAdresse(dto.getAdresse());
    }
}
