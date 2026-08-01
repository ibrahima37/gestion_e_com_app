package maboutique.shop.paiementservice.gestionPaiement.models;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "utilisateur-service", url = "http://localhost:8084/api/utilisateurs")
public interface UtilisateurClient {

}
