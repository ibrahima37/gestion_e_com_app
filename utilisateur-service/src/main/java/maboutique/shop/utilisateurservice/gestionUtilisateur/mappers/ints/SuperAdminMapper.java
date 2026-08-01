package maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.SuperAdminDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.SuperAdmin;

public interface SuperAdminMapper{

    SuperAdminDto toDto(SuperAdmin entity);

    SuperAdmin toEntity(SuperAdminDto dto);
}