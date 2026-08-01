package maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation.CompteCreationDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.CompteDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Compte;

public interface CompteMapper{

    CompteDto toDto(Compte entity);

    Compte toEntity(CompteDto dto);

    Compte toEntity(CompteCreationDto dto);
}
