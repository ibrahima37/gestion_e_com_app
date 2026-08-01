package maboutique.shop.utilisateurservice.gestionUtilisateur.ws;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.SuperAdminDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.SuperAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/superadmins")
@RequiredArgsConstructor
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    @PostMapping
    public ResponseEntity<SuperAdminDto> creerSuperAdmin(@Valid @RequestBody SuperAdminDto dto) {
        return ResponseEntity.ok(superAdminService.creerSuperAdmin(dto));
    }

    @PutMapping("/{id}/niveau")
    public ResponseEntity<SuperAdminDto> modifierNiveauAcces(@PathVariable UUID id,
                                                             @RequestParam int niveau) {
        return ResponseEntity.ok(superAdminService.modifierNiveauAcces(id, niveau));
    }

    @PutMapping("/{id}/permissions")
    public ResponseEntity<Void> gererPermissions(@PathVariable UUID id,
                                                 @RequestBody List<UUID> permissionIds) {
        superAdminService.gererPermissions(id, permissionIds);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{superAdminId}/attribuer-profil/{personneId}/{profilAdminId}")
    public ResponseEntity<Void> attribuerProfilAdmin(@PathVariable UUID superAdminId,
                                                     @PathVariable UUID personneId,
                                                     @PathVariable UUID profilAdminId) {
        superAdminService.attribuerProfilAdmin(superAdminId, personneId, profilAdminId);
        return ResponseEntity.noContent().build();
    }
}

