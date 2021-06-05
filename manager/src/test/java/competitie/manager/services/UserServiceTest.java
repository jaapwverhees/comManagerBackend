package competitie.manager.services;

import competitie.manager.models.user.User;
import competitie.manager.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void verifyTest_nullInput_NullPointerException() {
        assertThrows(NullPointerException.class, () -> userService.verify(null, ""));

        assertThrows(NullPointerException.class, () -> userService.verify("", null));

        verify(userRepository, never())
                .findById(any());
    }

    @Test
    void verifyTest_validInput_true() {
        when(userRepository.findById("email"))
                .thenReturn(of(User.builder()
                        .email("email")
                        .password("password")
                        .build()));

        assertTrue(userService.verify("email", "password"));

        verify(userRepository, times(1))
                .findById("email");
    }

    @Test
    void verifyTest_inValidInput_false() {
        when(userRepository.findById("email"))
                .thenReturn(empty());

        assertFalse(userService.verify("email", "password"));

        verify(userRepository, times(1))
                .findById("email");
    }
}
