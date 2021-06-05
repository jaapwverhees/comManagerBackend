package competitie.manager.controllers;

import competitie.manager.services.UserService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/validate/{email}/{password}")
    public boolean validate(@PathVariable @NonNull String email, @PathVariable @NonNull String password){
        return userService.verify(email, password);
    }
}
