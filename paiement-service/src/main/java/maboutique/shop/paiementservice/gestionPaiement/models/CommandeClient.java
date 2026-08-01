package maboutique.shop.paiementservice.gestionPaiement.models;

import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.CommandeDto;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutCommande;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "commande-service", url = "http://localhost:8081/api/commandes")
public interface CommandeClient {

//    @GetMapping("/{id}")
//    CommandeDto obtenirCommande(@PathVariable("id") UUID id);

    @GetMapping("/api/commande/{id}")
    CommandeDto getCommandeById(@PathVariable UUID id);

    @GetMapping("/{id}/exists")
    boolean existeCommande(@PathVariable("id") UUID id);

    @PutMapping("/{id}/statut")
    void changerStatut(@PathVariable("id") UUID id,
                           @RequestParam("statut") StatutCommande statut);
}
