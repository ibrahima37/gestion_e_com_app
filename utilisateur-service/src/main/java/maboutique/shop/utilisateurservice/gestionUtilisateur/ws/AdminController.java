package maboutique.shop.utilisateurservice.gestionUtilisateur.ws;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.AdminDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminDto> creerAdmin(@Valid @RequestBody AdminDto dto) {
        return ResponseEntity.ok(adminService.creerAdmin(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerAdmin(@PathVariable UUID id) {
        adminService.supprimerAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> modifierAdmin(@PathVariable UUID id,
                                                  @Valid @RequestBody AdminDto dto) {
        adminService.modifierAdmin(id, dto);
        return ResponseEntity.ok(dto);
    }
}

