package maboutique.shop.utilisateurservice.gestionUtilisateur.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhone {

    String message() default "Numéro de téléphone invalide";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

