package maboutique.shop.utilisateurservice.gestionUtilisateur.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return phone != null && ValidationUtils.isValidPhone(phone);
    }
}
