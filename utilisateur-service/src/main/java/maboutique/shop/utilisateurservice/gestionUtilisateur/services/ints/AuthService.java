package maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation.ChangerMotDePasseDto;import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation.ConnexionDto;import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation.InscriptionDto;import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation.ReinitialisationMotDePasseDto;import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.AuthResponseDto;import java.util.UUID;

public interface AuthService {

    AuthResponseDto inscription(InscriptionDto dto);

    AuthResponseDto connexion(ConnexionDto dto);

    void changerMotDePasse(UUID utilisateurId, ChangerMotDePasseDto dto);

    void motDePasseOublie(String email);

    void reinitialiserMotDePasse(ReinitialisationMotDePasseDto dto);

    void deconnexion();
}
