package ankvel.edu.security.logreg.repository;

import ankvel.edu.security.logreg.config.CacheConfig;
import ankvel.edu.security.logreg.domain.SomeUser;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SomeUser, Long> {

    @Cacheable(CacheConfig.USERS_CACHE)
    Optional<SomeUser> findByEmail(String email);

    @Override
    @CachePut(value = CacheConfig.USERS_CACHE, key = "#user.email")
    <S extends SomeUser> S save(S user);

    @Override
    @CacheEvict(value = CacheConfig.USERS_CACHE, key = "#user.email")
    void delete(SomeUser user);
}
