package maboutique.shop.boutiqueservice.gestionBoutique.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class ValidationErrorResponse {

    private int status;
    private String message;
    private Map<String, String> errors;

}
