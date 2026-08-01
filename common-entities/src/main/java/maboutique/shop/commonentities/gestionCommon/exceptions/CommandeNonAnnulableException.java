package maboutique.shop.commonentities.gestionCommon.exceptions;

public class CommandeNonAnnulableException extends RuntimeException {
    public CommandeNonAnnulableException(String message) {
        super(message);
    }
}
