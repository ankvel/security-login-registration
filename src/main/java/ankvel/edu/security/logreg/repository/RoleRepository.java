package ankvel.edu.security.logreg.repository;

import ankvel.edu.security.logreg.domain.SomeRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<SomeRole, Long> {

    Optional<SomeRole> findByName(String name);

}
