package maboutique.shop.paiementservice.gestionPaiement.ws;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.PaiementDto;
import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.PaiementRequestDto;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutPaiement;
import maboutique.shop.paiementservice.gestionPaiement.services.ints.PaiementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final PaiementService paiementService;

    // ✅ Effectuer un paiement
    @PostMapping
    public ResponseEntity<PaiementDto> effectuerPaiement(@RequestBody @Valid PaiementRequestDto dto) {
        PaiementDto paiement = paiementService.effectuerPaiement(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(paiement);
    }

    // ✅ Lister tous les paiements
    @GetMapping
    public ResponseEntity<List<PaiementDto>> listerPaiements() {
        List<PaiementDto> paiements = paiementService.listerPaiements();
        return ResponseEntity.ok(paiements);
    }

    @GetMapping("/commande/{commandeId}")
    public ResponseEntity<List<PaiementDto>> listerParCommande(@PathVariable UUID commandeId) {
        return ResponseEntity.ok(paiementService.listerParCommande(commandeId));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<PaiementDto>> listerParStatut(@PathVariable StatutPaiement statut) {
        return ResponseEntity.ok(paiementService.listerParStatut(statut));
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<PaiementDto>> listerParUtilisateur(@PathVariable UUID utilisateurId) {
        return ResponseEntity.ok(paiementService.findPaiementsByUtilisateur(utilisateurId));
    }
}
