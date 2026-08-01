package maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation.ProfilCreationDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.ProfilDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Profil;

public interface ProfilMapper {

    Profil toEntity(ProfilCreationDto dto);

    ProfilDto toDto(Profil profil);

    Profil toEntity(ProfilDto dto);

}