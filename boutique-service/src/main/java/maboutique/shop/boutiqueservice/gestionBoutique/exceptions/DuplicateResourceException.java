package maboutique.shop.boutiqueservice.gestionBoutique.exceptions;


public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}