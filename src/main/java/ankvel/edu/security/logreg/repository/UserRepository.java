package ankvel.edu.security.logreg.repository;

import ankvel.edu.security.logreg.model.SomeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SomeUser, Long> {
    Optional<SomeUser> findByEmail(String email);
}
