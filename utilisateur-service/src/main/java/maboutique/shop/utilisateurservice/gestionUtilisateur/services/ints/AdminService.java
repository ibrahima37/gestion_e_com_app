package maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.AdminDto;

import java.util.UUID;

public interface AdminService {

    AdminDto creerAdmin(AdminDto dto);
    void supprimerAdmin(UUID adminId);
    void modifierAdmin(UUID adminId, AdminDto dto);



}
