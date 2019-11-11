package ankvel.edu.security.logreg.repository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@ContextConfiguration(initializers = {UserRepositoryContainerTest.Initializer.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it-test")
@Testcontainers
public class UserVerificationRepositoryContainerTest {



}
