//package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;
//
//import maboutique.shop.boutiqueservice.gestionBoutique.dtos.paiement.PaiementDto;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@FeignClient(name = "paiement-service", url = "http://localhost:8083")
//public interface PaiementClient {
//
//    @PostMapping("/paiements")
//    PaiementDto effectuerPaiement(@RequestBody PaiementRequest request);
//}
