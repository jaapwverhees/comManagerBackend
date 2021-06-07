package competitie.manager.services.security;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Optional.ofNullable;

public class PasswordValidation {

    private static final String regex = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$";

    public static boolean
    isValidPassword(String password) {
        return ofNullable(password)
                .map(pw -> Pattern.compile(regex)
                        .matcher(pw)
                        .matches())
                .orElse(false);
    }
}
