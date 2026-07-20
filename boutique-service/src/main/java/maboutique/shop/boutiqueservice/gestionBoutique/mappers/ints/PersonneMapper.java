package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.PersonneDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.UserDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Personne;

public interface PersonneMapper {

    PersonneDto toDto(Personne personne);

    Personne toEntity(PersonneDto dto);

    // Conversion Personne -> UserDto
    UserDto toUserDto(Personne personne);

    void updateEntityFromDto(UserDto dto, Personne personne);

}
