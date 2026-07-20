package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.*;

import java.util.UUID;

public interface AuthService {

    AuthResponseDto inscription(InscriptionDto dto);

    AuthResponseDto connexion(ConnexionDto dto);

    void changerMotDePasse(UUID utilisateurId, ChangerMotDePasseDto dto);

    void motDePasseOublie(String email);

    void reinitialiserMotDePasse(ReinitialisationMotDePasseDto dto);

    void deconnexion();
}
