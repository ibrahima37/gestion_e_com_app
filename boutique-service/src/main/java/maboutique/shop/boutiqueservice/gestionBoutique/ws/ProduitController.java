package maboutique.shop.boutiqueservice.gestionBoutique.ws;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @PostMapping
    @PreAuthorize("hasAuthority('CREER_PRODUIT')")
    public ResponseEntity<ProduitDto> creerProduit(@Valid @RequestBody ProduitRequestDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(produitService.creerProduit(dto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TROUVER_PRODUIT')")
    public ResponseEntity<ProduitDto> trouverParId(@PathVariable UUID id) {

        return ResponseEntity.ok(produitService.trouverParId(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('LISTER_PRODUIT')")
    public ResponseEntity<List<ProduitDto>> listerProduits() {

        return ResponseEntity.ok(produitService.listerProduits());
    }

    @GetMapping("/recherche")
    @PreAuthorize("hasAuthority('RECHERCHER_PRODUIT')")
    public ResponseEntity<List<ProduitDto>> rechercherParNom(@RequestParam String nom) {

        return ResponseEntity.ok(produitService.rechercherParNom(nom));
    }

    @GetMapping("/categorie/{categorieId}")
    @PreAuthorize("hasAuthority('RECHERCHER_PRODUIT_CATEGORIE')")
    public ResponseEntity<List<ProduitDto>> rechercherParCategorie(@PathVariable UUID categorieId) {

        return ResponseEntity.ok(produitService.rechercherParCategorie(categorieId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPPRIMER_PRODUIT')")
    public ResponseEntity<Void> supprimerProduit(@PathVariable UUID id) {

        produitService.supprimerProduit(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/correction")
    @PreAuthorize("hasAuthority('CORRIGER_STOCK')")
    public ResponseEntity<Void> corrigerStock(@PathVariable UUID id, @RequestParam int nouvelleQuantite) {

        produitService.corrigerStock(id, nouvelleQuantite);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/disponible")
    @PreAuthorize("hasAuthority('PRODUIT_DISPONIBLE')")
    public ResponseEntity<Boolean> estDisponible(@PathVariable UUID id) {

        return ResponseEntity.ok(produitService.estDisponible(id));
    }

    @GetMapping("/promotions")
    @PreAuthorize("hasAuthority('PRODUIT_PROMOTION')")
    public ResponseEntity<List<ProduitDto>> produitsEnPromotion() {

        return ResponseEntity.ok(produitService.listerProduitsEnPromotion());
    }
}
