package maboutique.shop.boutiqueservice.gestionBoutique.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProductCodeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProductCode {
    String message() default "Code produit invalide";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
