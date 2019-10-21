package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeRole;
import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.dto.UserRegistrationCompleteEvent;
import ankvel.edu.security.logreg.dto.UserRegistrationRequest;
import ankvel.edu.security.logreg.exception.UserAlreadyExistsException;
import ankvel.edu.security.logreg.repository.RoleRepository;
import ankvel.edu.security.logreg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Collections.singletonList;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {


    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserRegistrationServiceImpl(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            ApplicationEventPublisher eventPublisher) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public SomeUser registerUser(UserRegistrationRequest registrationRequest) {

        if (emailExists(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException(registrationRequest.getEmail());
        }
        SomeUser user = createUserFromRegistrationRequest(registrationRequest);
        SomeUser savedUser = userRepository.save(user);
        eventPublisher.publishEvent(new UserRegistrationCompleteEvent(user));
        return savedUser;
    }

    private SomeUser createUserFromRegistrationRequest(UserRegistrationRequest registrationRequest) {

        SomeUser someUser = new SomeUser();
        someUser.setEmail(registrationRequest.getEmail());
        someUser.setName(registrationRequest.getName());
        someUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        SomeRole role = roleRepository.findByName("ROLE_SOME_USER");
        someUser.setRoles(singletonList(role));
        return someUser;
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
