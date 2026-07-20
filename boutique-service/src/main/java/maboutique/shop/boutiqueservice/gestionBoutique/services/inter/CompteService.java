package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.models.UtilisateurModel;
import org.springframework.http.ResponseEntity;

public interface CompteService {
    ResponseEntity<?> save_utilisateur(UtilisateurModel dto);
    ResponseEntity<?> cree_compte(UtilisateurModel dto);
}
