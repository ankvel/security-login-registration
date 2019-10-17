package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerifyToken;
import ankvel.edu.security.logreg.repository.UserRepository;
import ankvel.edu.security.logreg.repository.UserVerifyTokenRepository;
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

    private final UserVerifyTokenRepository userVerifyTokenRepository;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserVerifyTokenRepository userVerifyTokenRepository) {
        this.userRepository = userRepository;
        this.userVerifyTokenRepository = userVerifyTokenRepository;
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
    public UserVerifyToken createVerifyToken(SomeUser user) {
        String token = UUID.randomUUID().toString();
        UserVerifyToken result = new UserVerifyToken(token, user);
        userVerifyTokenRepository.save(result);
        return result;
    }
}
