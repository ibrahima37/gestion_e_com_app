package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

public interface MailService {

    void envoyerEmail(
            String destinataire,
            String sujet,
            String contenu
    );
}