package maboutique.shop.utilisateurservice.gestionUtilisateur.ws;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.PersonneDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.PersonneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personnes")
@RequiredArgsConstructor
public class PersonneController {

    private final PersonneService personneService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonneDto> getPersonne(@PathVariable UUID id) {
        return ResponseEntity.ok(personneService.trouverParId(id));
    }

    @GetMapping
    public ResponseEntity<List<PersonneDto>> getAllPersonnes() {
        return ResponseEntity.ok(personneService.trouverTous());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonneDto> updatePersonne(@PathVariable UUID id,
                                                      @Valid @RequestBody PersonneDto dto) {
        return ResponseEntity.ok(personneService.modifier(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable UUID id) {
        personneService.supprimer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<PersonneDto> searchByEmail(@RequestParam String email) {
        return ResponseEntity.ok(personneService.rechercherParEmail(email));
    }
}

