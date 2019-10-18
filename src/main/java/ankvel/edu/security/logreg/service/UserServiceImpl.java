package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import ankvel.edu.security.logreg.repository.UserRepository;
import ankvel.edu.security.logreg.repository.UserVerificationRepository;
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

    private final UserVerificationRepository userVerificationRepository;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserVerificationRepository userVerificationRepository) {
        this.userRepository = userRepository;
        this.userVerificationRepository = userVerificationRepository;
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
    public UserVerification createVerificationToken(SomeUser user) {
        String token = UUID.randomUUID().toString();
        UserVerification result = new UserVerification(token, user);
        userVerificationRepository.save(result);
        return result;
    }
}
