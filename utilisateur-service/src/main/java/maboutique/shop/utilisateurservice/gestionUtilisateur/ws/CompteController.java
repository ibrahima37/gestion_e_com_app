package maboutique.shop.utilisateurservice.gestionUtilisateur.ws;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maboutique.shop.utilisateurservice.gestionUtilisateur.models.UtilisateurModel;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.CompteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
public class CompteController {

    private final CompteService compteService;

    @PostMapping("/utilisateur")
    public ResponseEntity<?> saveUtilisateur(@Valid @RequestBody UtilisateurModel dto) {
        return compteService.save_utilisateur(dto);
    }

    @PostMapping("/creer")
    public ResponseEntity<?> creerCompte(@Valid @RequestBody UtilisateurModel dto) {
        return compteService.cree_compte(dto);
    }
}

