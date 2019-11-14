package ankvel.edu.security.logreg.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Arrays.asList;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String USERS_CACHE = "users";

    public static final String USER_ROLES_CACHE = "userRoles";

    @Bean
    public CacheManagerCustomizer<ConcurrentMapCacheManager> someCacheManagerCustomizer() {
        return cacheManager -> cacheManager.setCacheNames(
                asList(USERS_CACHE, USER_ROLES_CACHE));
    }
}
