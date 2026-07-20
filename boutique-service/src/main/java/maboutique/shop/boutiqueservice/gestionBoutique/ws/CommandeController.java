package maboutique.shop.boutiqueservice.gestionBoutique.ws;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande.CommandeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande.CommandeRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.enums.StatutCommande;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.CommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;

    // Créer une commande directement (hors panier)
    @PostMapping
    public ResponseEntity<CommandeDto> creerCommande(@RequestBody @Valid CommandeRequestDto dto) {

        CommandeDto commande = commandeService.creerCommande(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commande);
    }

    // Récupérer une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<CommandeDto> trouverParId(@PathVariable UUID id) {

        CommandeDto commande = commandeService.trouverParId(id);
        return ResponseEntity.ok(commande);
    }

    // Lister toutes les commandes d’un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommandeDto>> listerCommandesParUser(@PathVariable UUID userId) {

        List<CommandeDto> commandes = commandeService.listerCommandesParUser(userId);
        return ResponseEntity.ok(commandes);
    }

    // Lister toutes les commandes (admin)
    @GetMapping
    @PreAuthorize("hasAuthority('VOIR_TOUTES_COMMANDES')")
    public ResponseEntity<List<CommandeDto>> listerToutesCommandes() {

        List<CommandeDto> commandes = commandeService.listerToutesCommandes();
        return ResponseEntity.ok(commandes);
    }

    // Changer le statut d’une commande
    @PatchMapping("/{id}/statut")
    @PreAuthorize("hasAuthority('MODIFIER_STATUT_COMMANDE')")
    public ResponseEntity<CommandeDto> changerStatut(@PathVariable UUID id,
                                                     @RequestParam StatutCommande nouveauStatut) {
        CommandeDto commande = commandeService.changerStatut(id, nouveauStatut);
        return ResponseEntity.ok(commande);
    }

    // Annuler une commande
    @PatchMapping("/{id}/annuler")
    public ResponseEntity<Void> annulerCommande(@PathVariable UUID id) {

        commandeService.annulerCommande(id);
        return ResponseEntity.noContent().build();
    }
}

