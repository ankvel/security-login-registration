package ankvel.edu.security.logreg.repository;

import ankvel.edu.security.logreg.config.CacheConfig;
import ankvel.edu.security.logreg.domain.SomeRole;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<SomeRole, Long> {

    @Cacheable(CacheConfig.USER_ROLES_CACHE)
    Optional<SomeRole> findByName(String name);

    @Override
    @CachePut(value = CacheConfig.USER_ROLES_CACHE, key = "#role.name")
    <S extends SomeRole> S save(S role);

    @Override
    @CacheEvict(value = CacheConfig.USER_ROLES_CACHE, key = "#role.name")
    void delete(SomeRole role);
}
