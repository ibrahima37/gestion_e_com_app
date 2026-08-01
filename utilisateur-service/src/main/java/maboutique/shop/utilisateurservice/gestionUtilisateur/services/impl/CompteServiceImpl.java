package maboutique.shop.utilisateurservice.gestionUtilisateur.services.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.utilisateurservice.gestionUtilisateur.config.JwtService;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.AuthResponseDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.PermissionDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.ProfilDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Compte;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Utilisateur;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.UtilisateurMapper;
import maboutique.shop.utilisateurservice.gestionUtilisateur.models.UtilisateurModel;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.CompteRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.PersonneRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.CompteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final PersonneRepository personneRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UtilisateurMapper userMapper;

    @Override
    public ResponseEntity<?> save_utilisateur(UtilisateurModel dto) {

        // Vérifier si l'email existe déjà
        if (compteRepository.findByEmail(dto.getPersonnes().getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Cette adresse email est déjà utilisée.");
        }

        // Création de l'entité Personne (Utilisateur)
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(dto.getPersonnes().getNom());
        utilisateur.setPrenoms(dto.getPersonnes().getPrenoms());
        utilisateur.setEmail(dto.getPersonnes().getEmail());
        utilisateur.setTelephone(dto.getPersonnes().getTelephone());
        utilisateur.setAdresse(dto.getPersonnes().getAdresse());
        utilisateur.setDateInscription(new Date());
        utilisateur.setStatut(true);

        personneRepository.save(utilisateur);

        return ResponseEntity.ok(userMapper.toDto(utilisateur));
    }

    @Override
    public ResponseEntity<?> cree_compte(UtilisateurModel dto) {
        // Vérifier si l'email existe déjà
        if (compteRepository.findByEmail(dto.getPersonnes().getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Cette adresse email est déjà utilisée.");
        }

        // Création du compte lié à l'utilisateur
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(dto.getPersonnes().getNom());
        utilisateur.setPrenoms(dto.getPersonnes().getPrenoms());
        utilisateur.setEmail(dto.getPersonnes().getEmail());
        utilisateur.setTelephone(dto.getPersonnes().getTelephone());
        utilisateur.setAdresse(dto.getPersonnes().getAdresse());
        utilisateur.setDateInscription(new Date());
        utilisateur.setStatut(true);

        Compte compte = Compte.builder()
                .email(dto.getPersonnes().getEmail())
                .motDePasse(passwordEncoder.encode(dto.getPersonnes().getMotDePasse()))
                .actived(true)
                .passwordReset(false)
                .titulaire(utilisateur)
                .build();

        compteRepository.save(compte);

        // Génération du token JWT
        UserDetails userDetails = userDetailsService.loadUserByUsername(compte.getEmail());
        String token = jwtService.generateToken(userDetails);

        AuthResponseDto response = AuthResponseDto.builder()
                .token(token)
                .utilisateur(userMapper.toDto(utilisateur))
                .profils(Optional.ofNullable(utilisateur.getProfils())
                        .orElse(Collections.emptySet())
                        .stream()
                        .map(profil -> ProfilDto.builder()
                                .id(profil.getId())
                                .code(profil.getCode())
                                .libelle(profil.getLibelle())
                                .superAdmin(profil.getSuperAdmin())
                                .permissions(profil.getPermissions()
                                        .stream()
                                        .map(permission -> PermissionDto.builder()
                                                .id(permission.getId())
                                                .nom(permission.getNom())
                                                .description(permission.getDescription())
                                                .build())
                                        .collect(Collectors.toSet()))
                                .nombrePersonnes(profil.getPersonnes().size())
                                .build())
                        .collect(Collectors.toList()))
                .permissions(utilisateur.getProfils()
                        .stream()
                        .flatMap(p -> p.getPermissions().stream())
                        .map(permission -> PermissionDto.builder()
                                .id(permission.getId())
                                .nom(permission.getNom())
                                .description(permission.getDescription())
                                .build())
                        .toList())
                .build();


        return ResponseEntity.ok(response);
    }
}
