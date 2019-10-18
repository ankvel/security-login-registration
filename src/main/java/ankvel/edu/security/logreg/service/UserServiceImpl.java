package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerificationToken;
import ankvel.edu.security.logreg.repository.UserRepository;
import ankvel.edu.security.logreg.repository.UserVerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Collections.emptyList;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserVerificationTokenRepository userVerificationTokenRepository;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserVerificationTokenRepository userVerificationTokenRepository) {
        this.userRepository = userRepository;
        this.userVerificationTokenRepository = userVerificationTokenRepository;
    }

    @Override
    public SomeUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if (auth instanceof AnonymousAuthenticationToken) {
            SomeUser user = new SomeUser();
            user.setName("Anonymous");
            user.setEmail("None");
            user.setRoles(emptyList());
            return user;
        }
        return userRepository.findByEmail(name).orElseThrow(() ->
                new UsernameNotFoundException("No user found with username: " + name));
    }

    @Override
    public UserVerificationToken createVerificationToken(SomeUser user) {
        String token = UUID.randomUUID().toString();
        UserVerificationToken result = new UserVerificationToken(token, user);
        userVerificationTokenRepository.save(result);
        return result;
    }
}
