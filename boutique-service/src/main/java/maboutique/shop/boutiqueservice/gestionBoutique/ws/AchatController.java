package maboutique.shop.boutiqueservice.gestionBoutique.ws;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.achat.AchatDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.achat.AchatRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.AchatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/achats")
@RequiredArgsConstructor
public class AchatController {

    private final AchatService achatService;

    @PostMapping
    @PreAuthorize("hasAuthority('CREER_ACHAT')")
    public ResponseEntity<AchatDto> creerAchat(@Valid @RequestBody AchatRequestDto dto, UUID fournisseurId) {

        AchatDto achat = achatService.creerAchat(dto, fournisseurId);

        return ResponseEntity.status(HttpStatus.CREATED).body(achat);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MODIFIER_ACHAT')")
    public ResponseEntity<AchatDto> modifierAchat(@PathVariable UUID id, @Valid @RequestBody AchatRequestDto dto) {

        AchatDto achat = achatService.modifierAchat(id, dto);

        return ResponseEntity.ok(achat);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TROUVER_ACHAT')")
    public ResponseEntity<AchatDto> trouverParId(@PathVariable UUID id) {

        AchatDto achat = achatService.trouverParId(id);

        return ResponseEntity.ok(achat);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('LISTER_ACHAT')")
    public ResponseEntity<List<AchatDto>> trouverTous() {

        List<AchatDto> achats = achatService.trouverTous();

        return ResponseEntity.ok(achats);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPPRIMER_ACHAT')")
    public ResponseEntity<Void> supprimer(@PathVariable UUID id) {

        achatService.supprimer(id);

        return ResponseEntity.noContent().build();
    }
}