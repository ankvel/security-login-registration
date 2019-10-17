package ankvel.edu.security.logreg.repository;

import ankvel.edu.security.logreg.domain.SomeRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<SomeRole, Long> {
    SomeRole findByName(String name);
}
