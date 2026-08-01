package maboutique.shop.utilisateurservice.gestionUtilisateur.utils;

import java.util.regex.Pattern;

public class ValidationUtils {

    // Regex pour email standard
    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    // Regex pour numéro de téléphone international (ex: +221 77 123 45 67)
    private static final String PHONE_REGEX =
            "^\\+?[0-9]{1,3}?[-.\\s]?\\(?[0-9]{1,4}?\\)?[-.\\s]?[0-9]{3,}$";

    // Regex pour mot de passe fort (au moins 8 caractères, majuscule, minuscule, chiffre, caractère spécial)
    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    // Validation email
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    // Validation numéro de téléphone
    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    // Validation mot de passe
    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    // Exemple pour formats spécifiques (ex: code produit alphanumérique)
    public static boolean isValidProductCode(String code) {
        return code != null && code.matches("^[A-Z0-9_-]{5,20}$");
    }
}
