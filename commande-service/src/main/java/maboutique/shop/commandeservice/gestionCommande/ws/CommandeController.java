package maboutique.shop.commandeservice.gestionCommande.ws;

import lombok.RequiredArgsConstructor;
import maboutique.shop.commandeservice.gestionCommande.dtos.commande.CommandeDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.commande.CommandeRequestDto;
import maboutique.shop.commandeservice.gestionCommande.enums.StatutCommande;
import maboutique.shop.commandeservice.gestionCommande.services.ints.CommandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;

    // Créer une commande
    @PostMapping
    public ResponseEntity<CommandeDto> creerCommande(@RequestBody CommandeRequestDto dto) {
        return ResponseEntity.ok(commandeService.creerCommande(dto));
    }

    // Récupérer une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<CommandeDto> trouverParId(@PathVariable UUID id) {
        return ResponseEntity.ok(commandeService.trouverParId(id));
    }

    // Lister les commandes d’un utilisateur
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<CommandeDto>> listerCommandesParUtilisateur(@PathVariable UUID utilisateurId) {
        return ResponseEntity.ok(commandeService.listerCommandesParUtilisateur(utilisateurId));
    }

    // Lister toutes les commandes (admin)
    @GetMapping
    public ResponseEntity<List<CommandeDto>> listerToutesCommandes() {
        return ResponseEntity.ok(commandeService.listerToutesCommandes());
    }

    // Changer le statut d’une commande
    @PutMapping("/{id}/statut")
    public ResponseEntity<CommandeDto> changerStatut(@PathVariable UUID id,
                                                     @RequestParam StatutCommande nouveauStatut) {
        return ResponseEntity.ok(commandeService.changerStatut(id, nouveauStatut));
    }

    // Annuler une commande
    @PutMapping("/{id}/annuler")
    public ResponseEntity<CommandeDto> annulerCommande(@PathVariable UUID id) {
        return ResponseEntity.ok(commandeService.annulerCommande(id));
    }

    // Mettre à jour livraison
    @PutMapping("/{id}/livraison")
    public ResponseEntity<CommandeDto> mettreAJourLivraison(@PathVariable UUID id,
                                                            @RequestParam String nouvelleAdresse,
                                                            @RequestParam String nouveauMode) {
        return ResponseEntity.ok(commandeService.mettreAJourLivraison(id, nouvelleAdresse, nouveauMode));
    }

    // Recalculer montant
    @PutMapping("/{id}/recalculer")
    public ResponseEntity<CommandeDto> recalculerMontant(@PathVariable UUID id) {
        return ResponseEntity.ok(commandeService.recalculerMontant(id));
    }

    // Lister par statut
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<CommandeDto>> listerCommandesParStatut(@PathVariable StatutCommande statut) {
        return ResponseEntity.ok(commandeService.listerCommandesParStatut(statut));
    }

    // Supprimer une commande (admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCommande(@PathVariable UUID id) {
        commandeService.supprimerCommande(id);
        return ResponseEntity.noContent().build();
    }
}

