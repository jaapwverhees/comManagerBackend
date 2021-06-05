package competitie.manager.services;

import competitie.manager.models.user.User;
import competitie.manager.repositories.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public boolean verify(@NonNull String email, @NonNull String password) {
        return repo.findById(email)
                .map(User::getPassword)
                .map(v -> v.equals(password))
                .orElse(false);
    }
}
