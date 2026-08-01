package maboutique.shop.commonentities.gestionCommon.errors;

import maboutique.shop.commonentities.gestionCommon.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResource(
            DuplicateResourceException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponseFactory.conflict(exception.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseFactory.notFound(exception.getMessage()));
    }

    @ExceptionHandler(SuperAdminNotFoundException.class)
    public ResponseEntity<String> handleSuperAdminNotFound(SuperAdminNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<String> handleAdminNotFound(AdminNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PersonneNotFoundException.class)
    public ResponseEntity<String> handlePersonneNotFound(PersonneNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseFactory.validation(errors));
    }

    @ExceptionHandler(StockInsuffisantException.class)
    public ResponseEntity<ValidationErrorResponse> handleStockInsuffisant(StockInsuffisantException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseFactory.validation(Map.of("stock", ex.getMessage())));
    }

    @ExceptionHandler(PaiementRefuseException.class)
    public ResponseEntity<ErrorResponse> handlePaiementRefuse(PaiementRefuseException ex) {
        return ResponseEntity
                .status(HttpStatus.PAYMENT_REQUIRED) // 402
                .body(new ErrorResponse(LocalDateTime.now(),
                        HttpStatus.PAYMENT_REQUIRED.value(),
                        "Payment Required",
                        ex.getMessage()));
    }

    @ExceptionHandler(CommandeNonAnnulableException.class)
    public ResponseEntity<ErrorResponse> handleCommandeNonAnnulable(CommandeNonAnnulableException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponseFactory.conflict(ex.getMessage()));
    }

}

