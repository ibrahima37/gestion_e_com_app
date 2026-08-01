package maboutique.shop.utilisateurservice.gestionUtilisateur.services.impl;

import maboutique.shop.commonentities.gestionCommon.exceptions.PersonneNotFoundException;
import maboutique.shop.commonentities.gestionCommon.exceptions.ResourceNotFoundException;
import maboutique.shop.commonentities.gestionCommon.exceptions.SuperAdminNotFoundException;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.SuperAdminDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Permission;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Personne;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Profil;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.SuperAdmin;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.SuperAdminMapper;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.PermissionRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.PersonneRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.ProfilRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.SuperAdminRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.SuperAdminService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SuperAdminServiceImpl implements SuperAdminService {

    private final SuperAdminRepository superAdminRepository;
    private final PersonneRepository personneRepository;
    private final PermissionRepository permissionRepository;
    private final ProfilRepository profilRepository;
    private final SuperAdminMapper superAdminMapper;


    @Override
    public SuperAdminDto creerSuperAdmin(SuperAdminDto dto) {

        SuperAdmin superAdmin = superAdminMapper.toEntity(dto);

        superAdmin.setNiveauAcces(1);

        superAdmin = superAdminRepository.save(superAdmin);

        return superAdminMapper.toDto(superAdmin);
    }


    @Override
    public SuperAdminDto modifierNiveauAcces(UUID id, int niveau) {

        SuperAdmin superAdmin = superAdminRepository.findById(id)
                .orElseThrow(() ->
                        new SuperAdminNotFoundException("Super administrateur introuvable"));

        superAdmin.setNiveauAcces(niveau);

        superAdmin = superAdminRepository.save(superAdmin);

        return superAdminMapper.toDto(superAdmin);
    }

    @Override
    public void gererPermissions(UUID utilisateurId, List<UUID> permissionIds) {

        Personne personne = personneRepository.findById(utilisateurId)
                .orElseThrow(() ->
                        new PersonneNotFoundException("Utilisateur introuvable"));

        Set<Permission> permissions =
                new HashSet<>(permissionRepository.findAllById(permissionIds));

        personne.getProfils().forEach(profil -> profil.getPermissions().addAll(permissions));

        personneRepository.save(personne);
    }

    @Override
    public void attribuerProfilAdmin(UUID superAdminId, UUID personneId, UUID profilAdminId) {

        SuperAdmin superAdmin = superAdminRepository.findById(superAdminId)
                .orElseThrow(() -> new SuperAdminNotFoundException("SuperAdmin introuvable"));

        Personne personne = personneRepository.findById(personneId)
                .orElseThrow(() -> new PersonneNotFoundException("Personne introuvable"));

        Profil profilAdmin = profilRepository.findById(profilAdminId)
                .orElseThrow(() -> new ResourceNotFoundException("Profil Admin introuvable"));

        // Attribution du profil Admin à la personne
        personne.getProfils().add(profilAdmin);
        personneRepository.save(personne);
    }

}