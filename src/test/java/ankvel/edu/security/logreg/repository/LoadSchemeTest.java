package ankvel.edu.security.logreg.repository;

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

@DataJpaTest
@ContextConfiguration(initializers = {LoadSchemeTest.Initializer.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it-test")
@Testcontainers
public class LoadSchemeTest {

    @BeforeEach
    public void init() {
        flyway.clean();
        flyway.migrate();
    }

    @Container
    private static final PostgreSQLContainer postgreSQLContainer =
            (PostgreSQLContainer) new PostgreSQLContainer()
                    .withDatabaseName("some-test")
                    .withUsername("some2")
                    .withPassword("some2")
                    .withStartupTimeout(Duration.ofSeconds(600));
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Flyway flyway;

    @Test
    void test() {

    }

    @Test
    void test2() {

    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.flyway.url=" + postgreSQLContainer.getJdbcUrl()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
