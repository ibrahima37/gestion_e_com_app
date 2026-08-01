package maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints;

import maboutique.shop.utilisateurservice.gestionUtilisateur.models.UtilisateurModel;
import org.springframework.http.ResponseEntity;

public interface CompteService {
    ResponseEntity<?> save_utilisateur(UtilisateurModel dto);
    ResponseEntity<?> cree_compte(UtilisateurModel dto);
}
