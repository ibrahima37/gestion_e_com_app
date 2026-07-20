package maboutique.shop.boutiqueservice.gestionBoutique.ws;

import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.categorie.CategorieDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.categorie.CategorieRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.CategorieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategorieController {

    private final CategorieService service;

    @PostMapping
    @PreAuthorize("hasAuthority('CREER_CATEGORIE')")
    public ResponseEntity<CategorieDto> creerCategorie(@RequestBody CategorieRequestDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.creerCategorie(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MODIFIER_CATEGORIE')")
    public ResponseEntity<CategorieDto> modifierCategorie(@PathVariable UUID id, @RequestBody CategorieDto dto) {

        return ResponseEntity.ok(service.modifierCategorie(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPPRIMER_CATEGORIE')")
    public ResponseEntity<Void> supprimerCategorie(@PathVariable UUID id) {

        service.supprimerCategorie(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('LISTER_CATEGORIE')")
    public ResponseEntity<List<CategorieDto>> listerCategories() {

        return ResponseEntity.ok(service.listerCategories());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('RECHERCHE_CATEGORIE')")
    public ResponseEntity<CategorieDto> rechercherParId(@PathVariable UUID id) {

        return ResponseEntity.ok(service.rechercherParId(id));
    }
}
