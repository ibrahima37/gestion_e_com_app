package maboutique.shop.boutiqueservice.gestionBoutique.ws;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande.CommandeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.panier.PanierDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.panier.PanierRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitPanierRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.PanierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/paniers")
@RequiredArgsConstructor
public class PanierController {

    private final PanierService panierService;

    // Créer un panier
    @PostMapping
    public ResponseEntity<PanierDto> creerPanier(@RequestBody @Valid PanierRequestDto dto) {
        PanierDto panier = panierService.creerPanier(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(panier);
    }

    // Ajouter un produit au panier
    @PostMapping("/{panierId}/produits")
    public ResponseEntity<PanierDto> ajouterProduit(@PathVariable UUID panierId,
                                                    @RequestBody @Valid ProduitPanierRequestDto produitDto) {
        PanierDto panier = panierService.ajouterProduit(panierId, produitDto);
        return ResponseEntity.ok(panier);
    }

    // Retirer un produit du panier
    @DeleteMapping("/{panierId}/produits/{produitPanierId}")
    public ResponseEntity<PanierDto> retirerProduit(@PathVariable UUID panierId,
                                                    @PathVariable UUID produitPanierId) {
        PanierDto panier = panierService.retirerProduit(panierId, produitPanierId);
        return ResponseEntity.ok(panier);
    }

    // Consulter un panier par ID
    @GetMapping("/{id}")
    public ResponseEntity<PanierDto> trouverParId(@PathVariable UUID id) {
        PanierDto panier = panierService.trouverParId(id);
        return ResponseEntity.ok(panier);
    }

    // Lister les paniers d’un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PanierDto>> listerPaniersParUser(@PathVariable UUID userId) {
        List<PanierDto> paniers = panierService.listerPaniersParUser(userId);
        return ResponseEntity.ok(paniers);
    }

    // Supprimer un panier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerPanier(@PathVariable UUID id) {
        panierService.supprimerPanier(id);
        return ResponseEntity.noContent().build();
    }

    // Valider un panier → transforme en commande
    @PostMapping("/{panierId}/valider")
    public ResponseEntity<CommandeDto> validerPanier(@PathVariable UUID panierId) {
        CommandeDto commande = panierService.validerPanier(panierId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commande);
    }
}
