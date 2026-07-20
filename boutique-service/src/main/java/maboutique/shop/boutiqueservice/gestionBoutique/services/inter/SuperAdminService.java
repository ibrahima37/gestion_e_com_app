package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.LogDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.RapportSystemeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.SuperAdminDto;

import java.util.List;
import java.util.UUID;

public interface SuperAdminService {

    // Gestion super administrateurs
    SuperAdminDto creerSuperAdmin(SuperAdminDto dto);

    SuperAdminDto modifierNiveauAcces(UUID id, int niveau);

    // Gestion sécurité
    List<LogDto> consulterLogs();

    void gererPermissions(UUID utilisateurId, List<UUID> permissionIds);

    // Rapports système
    RapportSystemeDto genererRapports();

}
