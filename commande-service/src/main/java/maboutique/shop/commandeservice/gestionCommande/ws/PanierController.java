package maboutique.shop.commandeservice.gestionCommande.ws;

import lombok.RequiredArgsConstructor;
import maboutique.shop.commandeservice.gestionCommande.dtos.commande.CommandeDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.PanierDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.PanierRequestDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.ProduitPanierRequestDto;
import maboutique.shop.commandeservice.gestionCommande.services.ints.PanierService;
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
    public ResponseEntity<PanierDto> creerPanier(@RequestBody PanierRequestDto dto) {
        return ResponseEntity.ok(panierService.creerPanier(dto));
    }

    // Ajouter un produit dans un panier
    @PostMapping("/{panierId}/produits")
    public ResponseEntity<PanierDto> ajouterProduit(@PathVariable UUID panierId,
                                                    @RequestBody ProduitPanierRequestDto produitDto) {
        return ResponseEntity.ok(panierService.ajouterProduit(panierId, produitDto));
    }

    // Mettre à jour la quantité d’un produit
    @PutMapping("/{panierId}/produits/{produitPanierId}")
    public ResponseEntity<PanierDto> mettreAJourQuantite(@PathVariable UUID panierId,
                                                         @PathVariable UUID produitPanierId,
                                                         @RequestParam Integer nouvelleQuantite) {
        return ResponseEntity.ok(panierService.mettreAJourQuantite(panierId, produitPanierId, nouvelleQuantite));
    }

    // Retirer un produit du panier
    @DeleteMapping("/{panierId}/produits/{produitPanierId}")
    public ResponseEntity<PanierDto> retirerProduit(@PathVariable UUID panierId,
                                                    @PathVariable UUID produitPanierId) {
        return ResponseEntity.ok(panierService.retirerProduit(panierId, produitPanierId));
    }

    // Récupérer un panier par ID
    @GetMapping("/{id}")
    public ResponseEntity<PanierDto> trouverParId(@PathVariable UUID id) {
        return ResponseEntity.ok(panierService.trouverParId(id));
    }

    // Lister les paniers d’un utilisateur
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<PanierDto>> listerPaniersParUtilisateur(@PathVariable UUID utilisateurId) {
        return ResponseEntity.ok(panierService.listerPaniersParUtilisateur(utilisateurId));
    }

    // Supprimer un panier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerPanier(@PathVariable UUID id) {
        panierService.supprimerPanier(id);
        return ResponseEntity.noContent().build();
    }

    // Vider un panier
    @DeleteMapping("/{panierId}/vider")
    public ResponseEntity<PanierDto> viderPanier(@PathVariable UUID panierId) {
        return ResponseEntity.ok(panierService.viderPanier(panierId));
    }

    // Valider un panier et le transformer en commande
    @PostMapping("/{panierId}/valider")
    public ResponseEntity<CommandeDto> validerPanier(@PathVariable UUID panierId) {
        return ResponseEntity.ok(panierService.validerPanier(panierId));
    }
}
