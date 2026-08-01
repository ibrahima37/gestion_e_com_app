package maboutique.shop.commonentities.gestionCommon.errors;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponseFactory {

    public static ErrorResponse conflict(String message) {
        return new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                message
        );
    }

    public static ErrorResponse notFound(String message) {
        return new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                message
        );
    }

    public static ValidationErrorResponse validation(Map<String, String> errors) {
        return ValidationErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Erreur de validation")
                .errors(errors)
                .build();
    }
}

