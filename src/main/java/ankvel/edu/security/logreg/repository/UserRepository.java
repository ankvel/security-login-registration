package ankvel.edu.security.logreg.repository;

import ankvel.edu.security.logreg.model.SomeUser;

import java.util.Optional;

public interface UserRepository {
    Optional<SomeUser> findByEmail(String email);
}
