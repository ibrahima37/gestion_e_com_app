package maboutique.shop.utilisateurservice.gestionUtilisateur.services.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.commonentities.gestionCommon.exceptions.DuplicateResourceException;
import maboutique.shop.commonentities.gestionCommon.exceptions.ResourceNotFoundException;
import maboutique.shop.commonsecurity.gestionSecurity.config.JwtService;
import maboutique.shop.utilisateurservice.gestionUtilisateur.securites.CustomUserDetailsService;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation.ChangerMotDePasseDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation.ConnexionDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation.InscriptionDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation.ReinitialisationMotDePasseDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.AuthResponseDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Compte;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.PasswordResetToken;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Personne;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Utilisateur;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.PermissionMapper;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.ProfilMapper;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.UtilisateurMapper;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.CompteRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.PasswordResetTokenRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.ProfilRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.AuthService;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.MailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final CompteRepository compteRepository;
    private final ProfilRepository profilRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final UtilisateurMapper userMapper;
    private final CustomUserDetailsService userDetailsService;
    private final MailService mailService;
    private final ProfilMapper profilMapper;
    private final PermissionMapper permissionMapper;

    @Override
    public AuthResponseDto inscription(InscriptionDto dto) {

        if (compteRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateResourceException(
                    "Cette adresse email est déjà utilisée."
            );
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(dto.getNom());
        utilisateur.setPrenoms(dto.getPrenoms());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setTelephone(dto.getTelephone());
        utilisateur.setAdresse(dto.getAdresse());
        utilisateur.setDateInscription(new Date());
        utilisateur.setStatut(true);

        Compte compte = Compte.builder()
                .email(dto.getEmail())
                .motDePasse(passwordEncoder.encode(dto.getMotDePasse()))
                .actived(true)
                .passwordReset(false)
                .titulaire(utilisateur)
                .build();

        compteRepository.save(compte);

        UserDetails userDetails = userDetailsService.loadUserByUsername(compte.getEmail());

        String token = jwtService.generateToken(userDetails);

        return AuthResponseDto.builder()
                .token(token)
                .utilisateur(userMapper.toDto(utilisateur))
                .profils(utilisateur.getProfils()
                        .stream()
                        .map(profilMapper::toDto)
                        .toList())
                .permissions(utilisateur.getProfils()
                        .stream()
                        .flatMap(p -> p.getPermissions().stream())
                        .map(permissionMapper::toDto)
                        .toList())
                .build();
    }

    @Override
    public AuthResponseDto connexion(ConnexionDto dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getMotDePasse())
        );

        Compte compte = compteRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Compte introuvable."
                        ));

        if (!compte.getActived()) {
            throw new IllegalStateException(
                    "Votre compte est désactivé."
            );
        }

        LocalDateTime maintenant = LocalDateTime.now();

        if (compte.getPremiereConnexion() == null) {
            compte.setPremiereConnexion(maintenant);
        }

        compte.setDerniereConnexion(maintenant);

        compteRepository.save(compte);

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(compte.getEmail());

        String token = jwtService.generateToken(userDetails);

        return AuthResponseDto.builder()
                .token(token)
                .profils(compte.getTitulaire().getProfils()
                        .stream()
                        .map(profilMapper::toDto)
                        .toList())
                .permissions(compte.getTitulaire().getProfils()
                        .stream()
                        .flatMap(p -> p.getPermissions().stream())
                        .map(permissionMapper::toDto)
                        .toList())
                .build();

    }

    @Override
    public void changerMotDePasse(
            UUID utilisateurId,
            ChangerMotDePasseDto dto) {

        Compte compte = compteRepository.findByTitulaireId(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException(
                                "Compte introuvable."
                ));

        if (!passwordEncoder.matches(
                dto.getAncienMotDePasse(),
                compte.getMotDePasse())) {

            throw new IllegalArgumentException(
                    "L'ancien mot de passe est incorrect."
            );
        }

        if (!dto.getNouveauMotDePasse()
                .equals(dto.getConfirmationMotDePasse())) {

            throw new IllegalArgumentException(
                    "La confirmation du mot de passe est incorrecte."
            );
        }

        if (passwordEncoder.matches(
                dto.getNouveauMotDePasse(),
                compte.getMotDePasse())) {

            throw new IllegalArgumentException(
                    "Le nouveau mot de passe doit être différent de l'ancien."
            );
        }

        compte.setMotDePasse(
                passwordEncoder.encode(dto.getNouveauMotDePasse()));

        compte.setPasswordReset(true);

        compteRepository.save(compte);
    }

    @Override
    public void motDePasseOublie(String email) {

        Compte compte = compteRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Aucun compte associé à cette adresse email."
                        ));

        //String token = UUID.randomUUID().toString();

        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        String token = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);

        PasswordResetToken resetToken = PasswordResetToken.builder()
                .token(token)
                .compte(compte)
                .dateExpiration(
                        LocalDateTime.now().plusMinutes(30))
                .build();

        passwordResetTokenRepository.save(resetToken);

        String lien = "http://localhost:8080/api/auth/reset-password?token=" + token;

        mailService.envoyerEmail(

                compte.getEmail(),

                "Réinitialisation de votre mot de passe",

                """
                <html>
                    <body>

                        <p>Bonjour,</p>

                        <p>Vous avez demandé la réinitialisation de votre mot de passe.</p>

                        <p>Cliquez sur le bouton :</p>

                        <a href="%s">Réinitialiser mon mot de passe </a>

                        <p> Ce lien expire dans 30 minutes. </p>

                        <p> Cordialement,<br> Votre boutique
                        </p>

                    </body>
                </html>
                """.formatted(lien)
        );
    }

    @Override
    public void reinitialiserMotDePasse(ReinitialisationMotDePasseDto dto) {

        PasswordResetToken resetToken =
                passwordResetTokenRepository
                        .findByToken(dto.getToken())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Le lien de réinitialisation est invalide."
                                ));

        if (resetToken.getDateExpiration()
                .isBefore(LocalDateTime.now())) {

            passwordResetTokenRepository.delete(resetToken);

            throw new IllegalArgumentException(
                    "Le lien de réinitialisation a expiré."
            );
        }

        if (!dto.getNouveauMotDePasse()
                .equals(dto.getConfirmationMotDePasse())) {

            throw new IllegalArgumentException(
                    "La confirmation du mot de passe est incorrecte."
            );
        }

        Compte compte = resetToken.getCompte();

        if (passwordEncoder.matches(
                dto.getNouveauMotDePasse(),
                compte.getMotDePasse())) {

            throw new IllegalArgumentException(
                    "Le nouveau mot de passe doit être différent de l'ancien."
            );
        }

        compte.setMotDePasse(
                passwordEncoder.encode(dto.getNouveauMotDePasse()));

        compte.setPasswordReset(true);

        compteRepository.save(compte);

        passwordResetTokenRepository.delete(resetToken);
    }

    @Override
    public void deconnexion() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            return;
        }

        String email = authentication.getName();

        Compte compte = compteRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Compte introuvable."));

        compte.setDerniereDeconnexion(LocalDateTime.now());

        compteRepository.save(compte);

        SecurityContextHolder.clearContext();
    }
}
