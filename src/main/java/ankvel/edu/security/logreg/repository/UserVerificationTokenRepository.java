package ankvel.edu.security.logreg.repository;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.stream.Stream;

public interface UserVerificationTokenRepository extends JpaRepository<UserVerificationToken, Long> {

    UserVerificationToken findByToken(String token);

    UserVerificationToken findByUser(SomeUser user);

    Stream<UserVerificationToken> findAllByExpireDateLessThan(Instant now);

    void deleteByExpireDateLessThan(Instant now);
}
