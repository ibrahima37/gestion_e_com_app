package maboutique.shop.utilisateurservice.gestionUtilisateur.ws;

import lombok.RequiredArgsConstructor;
import maboutique.shop.commonentities.gestionCommon.dto.FournisseurDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.FournisseurService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fournisseurs")
public class FournisseurController {

    private final FournisseurService fournisseurService;

    @GetMapping("/{id}")
    public FournisseurDto getFournisseur(@PathVariable UUID id) {
        return fournisseurService.findById(id);
    }
}
