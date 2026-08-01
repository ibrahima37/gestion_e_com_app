package maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints;

public interface MailService {

    void envoyerEmail(
            String destinataire,
            String sujet,
            String contenu
    );
}