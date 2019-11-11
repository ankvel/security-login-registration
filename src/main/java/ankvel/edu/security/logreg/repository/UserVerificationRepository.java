package ankvel.edu.security.logreg.repository;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Stream;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Long> {

    Optional<UserVerification> findByTokenAndType(String token, UserVerification.Type type);

    Optional<UserVerification> findByUserAndType(SomeUser user, UserVerification.Type type);

    Stream<UserVerification> findAllByExpireDateLessThan(Instant now);

    void deleteByExpireDateLessThan(Instant now);
}
