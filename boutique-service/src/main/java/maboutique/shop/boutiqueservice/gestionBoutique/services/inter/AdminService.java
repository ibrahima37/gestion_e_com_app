package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.AdminDto;

import java.util.UUID;

public interface AdminService {

    // Gestion administrateurs
    AdminDto creerAdmin(AdminDto dto);
    void supprimerAdmin(UUID adminId);
    void modifierAdmin(UUID adminId, AdminDto dto);

    // Stock
    void mettreAJourStock(UUID produitId, Integer quantite);

}
