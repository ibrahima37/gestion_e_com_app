package maboutique.shop.commandeservice.gestionCommande.models;

import maboutique.shop.commandeservice.gestionCommande.dtos.ProduitDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "boutique-service")
public interface ProduitClient {

    @GetMapping("/api/produit/{id}")
    ProduitDTO getProduitById(@PathVariable UUID id);
}