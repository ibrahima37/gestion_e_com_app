package maboutique.shop.utilisateurservice.gestionUtilisateur.services.impl;

import maboutique.shop.commonentities.gestionCommon.exceptions.DuplicateResourceException;
import maboutique.shop.commonentities.gestionCommon.exceptions.ResourceNotFoundException;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation.ProfilCreationDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.ProfilDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Permission;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Personne;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Profil;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.ProfilMapper;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.UtilisateurMapper;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.PermissionRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.PersonneRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.ProfilRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.ProfilService;
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
public class ProfilServiceImpl implements ProfilService {

    private final ProfilRepository profilRepository;
    private final PersonneRepository personneRepository;
    private final PermissionRepository permissionRepository;
    private final ProfilMapper profilMapper;
    private final UtilisateurMapper userMapper;

    @Override
    public ProfilDto creerProfil(ProfilCreationDto dto) {

        if(profilRepository.existsByCode(dto.getCode())){

            throw new DuplicateResourceException("Le code existe déjà");
        }

        Profil profil = profilMapper.toEntity(dto);

        if(dto.getPermissionIds()!=null && !dto.getPermissionIds().isEmpty()){

            Set<Permission> permissions =
                    new HashSet<>(
                            permissionRepository.findAllById(
                                    dto.getPermissionIds()
                            )
                    );

            profil.setPermissions(permissions);
        }

        return profilMapper.toDto(profilRepository.save(profil));
    }

    @Override
    public ProfilDto modifierProfil(UUID id, ProfilCreationDto dto) {

        Profil profil = profilRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Profil introuvable"));

        profil.setCode(dto.getCode());

        profil.setLibelle(dto.getLibelle());

        profil.setSuperAdmin(dto.getSuperAdmin());

        if(dto.getPermissionIds()!=null){

            Set<Permission> permissions =
                    new HashSet<>(
                            permissionRepository.findAllById(dto.getPermissionIds())
                    );

            profil.setPermissions(permissions);
        }

        return profilMapper.toDto(profilRepository.save(profil));
    }

    @Override
    public void supprimerProfil(UUID id) {

        Profil profil = profilRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profil introuvable"));

        profil.getPermissions().clear();
        profil.getPersonnes().forEach(
                personne -> personne.getProfils().remove(profil)
        );

        profilRepository.delete(profil);
    }

    @Override
    @Transactional(readOnly = true)
    public ProfilDto trouverParId(UUID id) {

        Profil profil = profilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profil introuvable"));

        return profilMapper.toDto(profil);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfilDto> listerProfils() {

        return profilRepository.findAll()
                .stream()
                .map(profilMapper::toDto)
                .toList();
    }

//    @Override
//    public UserDetailDto attribuerProfil(UUID personneId, UUID profilId) {
//
//        Personne personne = personneRepository.findById(personneId)
//                .orElseThrow(() -> new ResourceNotFoundException("Personne introuvable"));
//
//        Profil profil = profilRepository.findById(profilId)
//                .orElseThrow(() -> new ResourceNotFoundException("Profil introuvable"));
//
//        if (!personne.getProfils().contains(profil)) {
//            personne.getProfils().add(profil);
//            personne = personneRepository.save(personne);
//        }
//
//        return userMapper.toDetailDto((User) personne);
//    }

    @Override
    public void retirerProfil(UUID personneId, UUID profilId) {

        Personne personne = personneRepository.findById(personneId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

        Profil profil = profilRepository.findById(profilId)
                .orElseThrow(() -> new ResourceNotFoundException("Profil introuvable"));

        personne.getProfils().remove(profil);

        personneRepository.save(personne);
    }
}
