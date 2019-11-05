package ankvel.edu.security.logreg;


import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@Testcontainers
public class SomePostgreSqlContainerTest {

    @Container
    public static final PostgreSQLContainer postgresContainer = new PostgreSQLContainer();

    @Test
    public void whenSelectQueryExecuted_thenResultsReturned() throws Exception {
        ResultSet resultSet = performQuery(postgresContainer, "SELECT 1");
        resultSet.next();
        int result = resultSet.getInt(1);
        assertEquals(1, result);
    }

    private ResultSet performQuery(PostgreSQLContainer postgres, String query) throws SQLException {

        String jdbcUrl = postgres.getJdbcUrl();
        String username = postgres.getUsername();
        String password = postgres.getPassword();
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        return conn.createStatement()
                .executeQuery(query);
    }
}