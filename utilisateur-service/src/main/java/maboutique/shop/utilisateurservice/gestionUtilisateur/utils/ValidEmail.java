package maboutique.shop.utilisateurservice.gestionUtilisateur.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    String message() default "Email invalide";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

