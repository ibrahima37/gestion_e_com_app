package maboutique.shop.utilisateurservice.gestionUtilisateur.ws;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.compte.ProfilCreationDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.compte.ProfilDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.compte.UserDetailDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.ProfilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/profils")
@RequiredArgsConstructor
public class ProfilController {

    private final ProfilService profilService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CREER_PROFIL')")
    public ProfilDto creerProfil(@Valid @RequestBody ProfilCreationDto dto) {

        return profilService.creerProfil(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MODIFIER_PROFIL')")
    public ProfilDto modifierProfil(@PathVariable UUID id, @Valid @RequestBody ProfilCreationDto dto) {

        return profilService.modifierProfil(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SUPPRIMER_PROFIL')")
    public void supprimerProfil(@PathVariable UUID id) {

        profilService.supprimerProfil(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TROUVER_PROFIL')")
    public ProfilDto trouverParId(@PathVariable UUID id) {

        return profilService.trouverParId(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('LISTER_PROFIL')")
    public List<ProfilDto> listerProfils() {

        return profilService.listerProfils();
    }

    @PostMapping("/admin/attribuer-profil/{personneId}/{profilId}")
    @PreAuthorize("hasAuthority('ATTRIBUER_PROFIL')")
    public ResponseEntity<UserDetailDto> attribuerProfil(@PathVariable UUID personneId, @PathVariable UUID profilId) {
        return ResponseEntity.ok(profilService.attribuerProfil(personneId, profilId));
    }

    @DeleteMapping("/{profilId}/personnes/{personneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('RETIRER_PROFIL')")
    public void retirerProfil(@PathVariable UUID profilId, @PathVariable UUID personneId) {

        profilService.retirerProfil(personneId, profilId);
    }
}
