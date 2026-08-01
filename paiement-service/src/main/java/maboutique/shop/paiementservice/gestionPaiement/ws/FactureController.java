package maboutique.shop.paiementservice.gestionPaiement.ws;

import lombok.RequiredArgsConstructor;
import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.FactureDto;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutFacture;
import maboutique.shop.paiementservice.gestionPaiement.services.ints.FactureService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/factures")
@RequiredArgsConstructor
public class FactureController {

    private final FactureService factureService;

    // ✅ Générer une facture à partir d'une commande
    @PostMapping("/commande/{commandeId}")
    @PreAuthorize("hasAuthority('GENERER_FACTURE')")
    public ResponseEntity<FactureDto> genererFacture(@PathVariable UUID commandeId) {
        FactureDto facture = factureService.genererFacture(commandeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(facture);
    }

    // ✅ Télécharger une facture en PDF
    @GetMapping("/{id}/pdf")
    @PreAuthorize("hasAuthority('TELECHARGER_FACTURE')")
    public ResponseEntity<byte[]> telechargerFacture(@PathVariable UUID id) {
        byte[] pdf = factureService.telechargerFacture(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=facture-" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    // ✅ Envoyer une facture par email
    @PostMapping("/{id}/email")
    @PreAuthorize("hasAuthority('ENVOYER_FACTURE')")
    public ResponseEntity<Void> envoyerFacture(@PathVariable UUID id, @RequestParam String email) {
        factureService.envoyerFacture(id, email);
        return ResponseEntity.noContent().build();
    }

    // ✅ Valider une facture
    @PutMapping("/{id}/valider")
    @PreAuthorize("hasAuthority('VALIDER_FACTURE')")
    public ResponseEntity<Void> validerFacture(@PathVariable UUID id) {
        factureService.validerFacture(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Supprimer une facture
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPPRIMER_FACTURE')")
    public ResponseEntity<Void> supprimerFacture(@PathVariable UUID id) {
        factureService.supprimerFacture(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Recherche par client
    @GetMapping("/client/{clientId}")
    @PreAuthorize("hasAuthority('TROUVER_FACTURE_CLIENT')")
    public ResponseEntity<List<FactureDto>> trouverParClient(@PathVariable UUID clientId) {
        return ResponseEntity.ok(factureService.trouverParClient(clientId));
    }

    // ✅ Recherche par commande
    @GetMapping("/commande/{commandeId}")
    @PreAuthorize("hasAuthority('TROUVER_FACTURE_COMMANDE')")
    public ResponseEntity<List<FactureDto>> trouverParCommande(@PathVariable UUID commandeId) {
        return ResponseEntity.ok(factureService.trouverParCommande(commandeId));
    }

    // ✅ Recherche par statut
    @GetMapping("/statut/{statut}")
    @PreAuthorize("hasAuthority('TROUVER_FACTURE_STATUT')")
    public ResponseEntity<List<FactureDto>> trouverParStatut(@PathVariable StatutFacture statut) {
        return ResponseEntity.ok(factureService.trouverParStatut(statut));
    }

    // ✅ Consulter les détails d'une facture
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('DETAIL_FACTURE')")
    public ResponseEntity<FactureDto> obtenirDetails(@PathVariable UUID id) {
        return ResponseEntity.ok(factureService.obtenirDetails(id));
    }

    // ✅ Lister toutes les factures
    @GetMapping
    @PreAuthorize("hasAuthority('LISTER_FACTURE')")
    public ResponseEntity<List<FactureDto>> listerFactures() {
        return ResponseEntity.ok(factureService.listerFactures());
    }
}

