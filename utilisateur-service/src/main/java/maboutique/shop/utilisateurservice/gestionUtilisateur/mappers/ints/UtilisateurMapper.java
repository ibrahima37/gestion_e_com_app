package maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.UtilisateurDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Utilisateur;

public interface UtilisateurMapper {

    UtilisateurDto toDto(Utilisateur entity);

    Utilisateur toEntity(UtilisateurDto dto);
}
