package ankvel.edu.security.logreg.repository;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerifyToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.stream.Stream;

public interface UserVerifyTokenRepository extends JpaRepository<UserVerifyToken, Long> {

    UserVerifyToken findByToken(String token);

    UserVerifyToken findByUser(SomeUser user);

    Stream<UserVerifyToken> findAllByExpireDateLessThan(Instant now);

    void deleteByExpireDateLessThan(Instant now);
}
