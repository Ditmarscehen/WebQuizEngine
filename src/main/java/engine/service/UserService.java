package engine.service;

import engine.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> getByEmail(String email);
    User create(User user);
    void add(User user);
}
