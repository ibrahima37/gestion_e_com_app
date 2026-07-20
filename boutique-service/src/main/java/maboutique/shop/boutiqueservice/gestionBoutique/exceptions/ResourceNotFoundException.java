package maboutique.shop.boutiqueservice.gestionBoutique.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}