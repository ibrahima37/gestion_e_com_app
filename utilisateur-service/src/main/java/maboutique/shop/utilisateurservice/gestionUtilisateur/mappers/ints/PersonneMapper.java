package maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.PersonneDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Personne;

public interface PersonneMapper {

    PersonneDto toDto(Personne personne);

    Personne toEntity(PersonneDto dto);

}
