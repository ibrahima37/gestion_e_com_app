package maboutique.shop.utilisateurservice.gestionUtilisateur.ws;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> inscription(@Valid @RequestBody InscriptionDto dto) {
        AuthResponseDto response = authService.inscription(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> connexion(@RequestBody ConnexionDto dto) {
        AuthResponseDto response = authService.connexion(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/change-password/{userId}")
    public ResponseEntity<Void> changerMotDePasse(@PathVariable UUID userId, @RequestBody ChangerMotDePasseDto dto) {

        authService.changerMotDePasse(userId, dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> motDePasseOublie(@RequestParam String email) {

        authService.motDePasseOublie(email);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> reinitialiserMotDePasse(@RequestBody ReinitialisationMotDePasseDto dto) {

        authService.reinitialiserMotDePasse(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> deconnexion() {

        authService.deconnexion();
        return ResponseEntity.noContent().build();
    }
}

