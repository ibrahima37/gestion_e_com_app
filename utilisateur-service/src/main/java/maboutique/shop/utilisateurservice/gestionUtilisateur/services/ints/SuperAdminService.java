package maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints;


import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.SuperAdminDto;

import java.util.List;
import java.util.UUID;

public interface SuperAdminService {

    SuperAdminDto creerSuperAdmin(SuperAdminDto dto);

    SuperAdminDto modifierNiveauAcces(UUID id, int niveau);

    void gererPermissions(UUID utilisateurId, List<UUID> permissionIds);

    void attribuerProfilAdmin(UUID superAdminId, UUID personneId, UUID profilAdminId);

}
