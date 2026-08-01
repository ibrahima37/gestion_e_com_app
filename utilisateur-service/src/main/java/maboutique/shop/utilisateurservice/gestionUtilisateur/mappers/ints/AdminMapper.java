package maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.AdminDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Admin;

public interface AdminMapper {

    AdminDto toDto(Admin entity);

    Admin toEntity(AdminDto dto);
}
