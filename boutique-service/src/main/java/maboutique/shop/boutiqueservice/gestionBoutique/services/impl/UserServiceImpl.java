package maboutique.shop.boutiqueservice.gestionBoutique.services.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.PersonneDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.ProfilDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.UserDetailDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.UserDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Personne;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.PersonneMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.PersonneRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final PersonneRepository personneRepository;
    private final PersonneMapper personneMapper;


    @Override
    @Transactional(readOnly = true)
    public List<PersonneDto> consulterUtilisateurs() {

        return personneRepository.findAll()
                .stream()
                .map(personneMapper::toDto)
                .toList();
    }


    @Override
    public void bloquerUtilisateur(UUID userId) {

        Personne personne = personneRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("Utilisateur introuvable"));

        personne.setStatut(false);

        personneRepository.save(personne);
    }


    @Override
    public void activerUtilisateur(UUID userId) {

        Personne personne = personneRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("Utilisateur introuvable"));

        personne.setStatut(true);

        personneRepository.save(personne);
    }


//    @Override
//    public UserDto mettreAJourProfil(UUID id, UserDto dto) {
//
//        Personne personne = personneRepository.findById(id)
//                .orElseThrow(() ->
//                        new RuntimeException("Utilisateur introuvable"));
//
//
//        personne.setNom(dto.getNom());
//        personne.setPrenoms(dto.getPrenoms());
//        personne.setEmail(dto.getEmail());
//        personne.setTelephone(dto.getTelephone());
//        personne.setAdresse(dto.getAdresse());
//
//
//        Personne updated = personneRepository.save(personne);
//
//        return personneMapper.toUserDto(updated);
//    }

    @Override
    @Transactional
    public UserDto mettreAJourProfil(UUID id, UserDto dto) {

        Personne personne = personneRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Utilisateur introuvable"));

        personneMapper.updateEntityFromDto(dto, personne);

        personneRepository.save(personne);

        return personneMapper.toUserDto(personne);
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetailDto consulterProfil(UUID id) {

        Personne personne = personneRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Utilisateur introuvable"));


        return UserDetailDto.builder()
                .id(personne.getId())
                .nom(personne.getNom())
                .prenoms(personne.getPrenoms())
                .email(personne.getEmail())
                .telephone(personne.getTelephone())
                .adresse(personne.getAdresse())
                .dateInscription(personne.getDateInscription())
                .statut(personne.getStatut())
                .profils(
                        personne.getProfils()
                                .stream()
                                .map(profil -> ProfilDto.builder()
                                        .id(profil.getId())
                                        .code(profil.getCode())
                                        .libelle(profil.getLibelle())
                                        .superAdmin(profil.getSuperAdmin())
                                        .build())
                                .collect(Collectors.toSet())
                )
                .build();
    }
}