package competitie.manager.controllers;

import competitie.manager.dto.UserResponse;
import competitie.manager.exceptions.InvalidCredentialsException;
import competitie.manager.exceptions.InvalidPasswordException;
import competitie.manager.models.user.User;
import competitie.manager.services.UserService;
import competitie.manager.services.security.JWTTokenService;
import competitie.manager.services.security.Secret;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static competitie.manager.services.security.HttpMessage.INVALID_CREDENTAILS;
import static competitie.manager.services.security.HttpMessage.INVALID_PASSWORD;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin(origins = "*") //http://localhost:4200
@AllArgsConstructor
public class UserController implements Secret {

    private final UserService userService;

    private final JWTTokenService jwtTokenService;


    @PostMapping("user")
    public ResponseEntity<?> login(@RequestBody User user) {
        try{
            return new ResponseEntity<>(UserResponse.of(userService.verify(user),
                    jwtTokenService.getJWTToken(user.getEmail())), OK);
        } catch (InvalidCredentialsException e){
            return new ResponseEntity<>(INVALID_CREDENTAILS, NOT_ACCEPTABLE);
        }
    }

    @PostMapping("user/create")
    public ResponseEntity<?> create(@RequestParam("email") String email, @RequestParam("password") String pwd) {
        try{
            return new ResponseEntity<>(UserResponse.of(userService.save(User.builder()
                    .password(pwd)
                    .email(email)
                    .build()),
                    jwtTokenService.getJWTToken(email)),
                    OK);
        } catch (InvalidPasswordException e){
            return new ResponseEntity<>(INVALID_PASSWORD, NOT_ACCEPTABLE);
        }
    }
}
