package maboutique.shop.boutiqueservice.gestionBoutique.exceptions;

public class PaiementRefuseException extends RuntimeException {
    public PaiementRefuseException(String message) {
        super(message);
    }
}
