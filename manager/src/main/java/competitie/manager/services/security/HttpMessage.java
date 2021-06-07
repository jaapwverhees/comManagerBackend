package competitie.manager.services.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpMessage {
    INVALID_PASSWORD("Invalid Password. A password is considered valid if all the following constraints are satisfied:\n" +
            "\n" +
            "It contains at least 8 characters and at most 20 characters.\n" +
            "It contains at least one digit.\n" +
            "It contains at least one upper case alphabet.\n" +
            "It contains at least one lower case alphabet.\n" +
            "It contains at least one special character which includes !@#$%&*()-+=^.\n" +
            "It doesn’t contain any white space."),
    INVALID_CREDENTAILS("Email or password is incorrect");
    private final String message;
}
