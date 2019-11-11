package ankvel.edu.security.logreg.repository;

import ankvel.edu.security.logreg.domain.SomeRole;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ContextConfiguration(initializers = {RoleRepositoryContainerTest.Initializer.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it-test")
@Testcontainers
class RoleRepositoryContainerTest {

    @Autowired
    private RoleRepository roleRepository;

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
    void shouldContainBasicRoles() {
        assertThat(roleRepository.findAll()).extracting(SomeRole::getName).containsExactlyInAnyOrder(
                "ROLE_SOME_ADMIN",
                "ROLE_SOME_USER"
        );
    }

    @Test
    void shouldSaveThenFindRole() {

        Optional<SomeRole> roleOpt = roleRepository.findByName("ROLE_SOME_TEST");
        assertThat(roleOpt).isNotPresent();

        SomeRole role = new SomeRole("ROLE_SOME_TEST");
        SomeRole savedRole = roleRepository.save(role);
        assertThat(savedRole.getId()).isNotNull();

        roleOpt = roleRepository.findByName("ROLE_SOME_TEST");
        assertThat(roleOpt).isPresent();
    }
}
