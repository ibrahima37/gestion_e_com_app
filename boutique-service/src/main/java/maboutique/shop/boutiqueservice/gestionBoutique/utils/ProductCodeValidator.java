package maboutique.shop.boutiqueservice.gestionBoutique.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductCodeValidator implements ConstraintValidator<ValidProductCode, String> {
    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        return code != null && ValidationUtils.isValidProductCode(code);
    }
}
