//package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;
//
//import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.FournisseurDto;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.UUID;
//
//@FeignClient(name = "utilisateur-service", url = "http://localhost:8081")
//public interface FournisseurClient {
//
//    @GetMapping("/fournisseurs/{id}")
//    FournisseurDto getFournisseur(@PathVariable UUID id);
//}
//
