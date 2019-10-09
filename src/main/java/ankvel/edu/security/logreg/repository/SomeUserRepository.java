package ankvel.edu.security.logreg.repository;

import ankvel.edu.security.logreg.model.SomeRole;
import ankvel.edu.security.logreg.model.SomeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Repository
public class SomeUserRepository implements UserRepository {

    private static final SomeRole USER_ROLE;

    @Autowired
    private PasswordEncoder passwordEncoder;

    static {
        USER_ROLE = new SomeRole();
        USER_ROLE.setName("SOME_USER");
    }

    private static final SomeRole ADMIN_ROLE;

    static {
        ADMIN_ROLE = new SomeRole();
        ADMIN_ROLE.setName("SOME_ADMIN");
    }


    @Override
    public Optional<SomeUser> findByEmail(String email) {

        if (email == null) {
            return Optional.empty();
        }

        switch (email) {
            case "some1@gmail.com": {
                SomeUser user = new SomeUser();
                user.setName("some1");
                user.setEmail("some1@gmail.com");
                user.setPassword(passwordEncoder.encode("some1"));
                user.setRoles(singletonList(USER_ROLE));
                return Optional.of(user);
            }
            case "some2@gmail.com": {
                SomeUser user = new SomeUser();
                user.setName("some2");
                user.setEmail("some2@gmail.com");
                user.setPassword(passwordEncoder.encode("some2"));
                user.setRoles(singletonList(USER_ROLE));
                return Optional.of(user);
            }
            case "some3@gmail.com": {
                SomeUser user = new SomeUser();
                user.setName("some3");
                user.setEmail("some3@gmail.com");
                user.setPassword(passwordEncoder.encode("some3"));
                user.setRoles(Arrays.asList(USER_ROLE, ADMIN_ROLE));
                return Optional.of(user);
            }
            default:
                return Optional.empty();
        }
    }

}
