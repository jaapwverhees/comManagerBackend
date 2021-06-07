package competitie.manager.services.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordAuthenticationTest {

    @Test
    void hash() {
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
        String hashed = passwordAuthentication.hash("hello There".toCharArray());
        assertTrue(passwordAuthentication.authenticate("hello There", hashed));
    }
}
