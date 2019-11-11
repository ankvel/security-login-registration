package ankvel.edu.security.logreg.repository;

import ankvel.edu.security.logreg.domain.SomeUser;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ContextConfiguration(initializers = {UserRepositoryContainerTest.Initializer.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it-test")
@Testcontainers
class UserRepositoryContainerTest {

    @Autowired
    private UserRepository userRepository;

    @Container
    private static final PostgreSQLContainer postgreSQLContainer =
            (PostgreSQLContainer) new PostgreSQLContainer()
                    .withDatabaseName("some-test")
                    .withUsername("some2")
                    .withPassword("some2")
                    .withStartupTimeout(Duration.ofSeconds(600));

    @Autowired
    private Flyway flyway;

    @BeforeEach
    void init() {
        flyway.clean();
        flyway.migrate();
    }

    protected static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.flyway.url=" + postgreSQLContainer.getJdbcUrl()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    void shouldContainBasicUsers() {
        assertThat(userRepository.findAll()).extracting(SomeUser::getName).containsExactlyInAnyOrder(
                "some1",
                "some2",
                "some3"
        );
    }
}
