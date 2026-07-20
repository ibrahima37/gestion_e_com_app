package maboutique.shop.boutiqueservice.gestionBoutique.exceptions;

public class StockInsuffisantException extends RuntimeException {
    public StockInsuffisantException(String message) {
        super(message);
    }
}

