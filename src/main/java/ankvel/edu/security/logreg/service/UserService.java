package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;

import java.util.Optional;

public interface UserService {

    SomeUser getCurrentUser();

    Optional<SomeUser> findByEmail(String email);

}
