package maboutique.shop.utilisateurservice.gestionUtilisateur.services.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.commonentities.gestionCommon.exceptions.AdminNotFoundException;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.AdminDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Admin;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.AdminMapper;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.AdminRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.AdminService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Override
    public AdminDto creerAdmin(AdminDto dto) {

        Admin admin = adminMapper.toEntity(dto);
        admin.setDateInscription(new Date());
        admin.setStatut(true);
        adminRepository.save(admin);
        return adminMapper.toDto(admin);
    }

    @Override
    public void supprimerAdmin(UUID adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new AdminNotFoundException("Admin introuvable"));
        adminRepository.delete(admin);
    }

    @Override
    public void modifierAdmin(UUID adminId, AdminDto dto) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new AdminNotFoundException("Admin introuvable"));

        // Mise à jour des champs
        admin.setNom(dto.getNom());
        admin.setPrenoms(dto.getPrenoms());
        admin.setEmail(dto.getEmail());
        admin.setTelephone(dto.getTelephone());
        admin.setAdresse(dto.getAdresse());
        admin.setStatut(dto.getStatut());

        adminRepository.save(admin);
    }
}

