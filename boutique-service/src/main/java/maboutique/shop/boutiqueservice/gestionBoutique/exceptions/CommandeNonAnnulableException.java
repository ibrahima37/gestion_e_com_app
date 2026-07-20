package maboutique.shop.boutiqueservice.gestionBoutique.exceptions;

public class CommandeNonAnnulableException extends RuntimeException {
    public CommandeNonAnnulableException(String message) {
        super(message);
    }
}
