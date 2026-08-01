package maboutique.shop.commonentities.gestionCommon.exceptions;

public class PaiementRefuseException extends RuntimeException {
    public PaiementRefuseException(String message) {
        super(message);
    }
}
