package maboutique.shop.commandeservice.gestionCommande.models;

import maboutique.shop.commandeservice.gestionCommande.dtos.UtilisateurDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "utilisateur-service")
public interface UtilisateurClient {

    @GetMapping("/api/utilisateur/{id}")
    UtilisateurDto getUtilisateurById(@PathVariable UUID id);
}

