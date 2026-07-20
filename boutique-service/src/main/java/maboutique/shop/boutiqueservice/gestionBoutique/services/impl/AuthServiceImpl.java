package maboutique.shop.boutiqueservice.gestionBoutique.services.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.config.JwtService;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.*;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Compte;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.PasswordResetToken;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.User;
import maboutique.shop.boutiqueservice.gestionBoutique.exceptions.DuplicateResourceException;
import maboutique.shop.boutiqueservice.gestionBoutique.exceptions.ResourceNotFoundException;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.UserMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.CompteRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.PasswordResetTokenRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.ProfilRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.UsersRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.securites.CustomUserDetailsService;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.AuthService;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.MailService;
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

    private final UsersRepository userRepository;
    private final CompteRepository compteRepository;
    private final ProfilRepository profilRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final CustomUserDetailsService userDetailsService;
    private final MailService mailService;

    @Override
    public AuthResponseDto inscription(InscriptionDto dto) {

        if (compteRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateResourceException(
                    "Cette adresse email est déjà utilisée."
            );
        }

        User utilisateur = User.builder()
                .nom(dto.getNom())
                .prenoms(dto.getPrenoms())
                .telephone(dto.getTelephone())
                .adresse(dto.getAdresse())
                .dateInscription(new Date())
                .statut(true)
                .build();

        utilisateur = userRepository.save(utilisateur);

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
                .utilisateur(userMapper.toDto((User) compte.getTitulaire()))
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
