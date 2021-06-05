package competitie.manager.controllers;

import competitie.manager.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    @Test
    void validateTest_emailIsNull_nullPointerException() {
        assertThrows(NullPointerException.class, () -> userController.validate(null, "password"));
        verify(userService, never())
                .verify(any(), any());
    }

    @Test
    void validateTest_passwordIsNull_nullPointerException() {
        assertThrows(NullPointerException.class, () ->
                userController.validate("hello", null));
        verify(userService, never())
                .verify(any(), any());
    }

    @Test
    void validateTest_validInput_true() {
        when(userService.verify(any(), any()))
                .thenReturn(true);
        assertTrue(userController.validate("hello", "goodbye"));
        verify(userService)
                .verify(any(), any());
    }
}
