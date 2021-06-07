package competitie.manager.services;

import competitie.manager.exceptions.InvalidCredentialsException;
import competitie.manager.exceptions.InvalidPasswordException;
import competitie.manager.models.user.User;
import competitie.manager.repositories.UserRepository;
import competitie.manager.services.security.PasswordAuthentication;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static competitie.manager.services.security.PasswordValidation.isValidPassword;

@Service
@Transactional
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User verify(@NonNull User request) {
        return repo.findById(request.getEmail())
                .filter(user ->
                    new PasswordAuthentication()
                            .authenticate(request.getPassword().toCharArray(), user.getPassword())) //TODO check of password als char[] gestuurd kan worden
                .orElseThrow(InvalidCredentialsException::new);
    }


    public User save(User user) throws InvalidPasswordException {
        if (isValidPassword(user.getPassword())){
            user.setPassword(new PasswordAuthentication().hash(user.getPassword()));
            return repo.save(user);
        }
        throw new InvalidPasswordException();
    }
}
