package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeRole;
import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.dto.UserRegistrationData;
import ankvel.edu.security.logreg.dto.UserRegistrationRequest;
import ankvel.edu.security.logreg.exception.UserAlreadyExistsException;
import ankvel.edu.security.logreg.repository.RoleRepository;
import ankvel.edu.security.logreg.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {


    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserRegistrationServiceImpl(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public SomeUser registerUser(
            UserRegistrationData userRegistrationData) {

        UserRegistrationRequest registrationRequest = userRegistrationData.getUserRegistrationRequest();

        if (emailExists(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException(registrationRequest.getEmail());
        }
        SomeUser user = createUserFromRegistrationRequest(registrationRequest);
        clearPasswordData(registrationRequest);
        return userRepository.save(user);
    }

    private SomeUser createUserFromRegistrationRequest(
            UserRegistrationRequest registrationRequest) {

        SomeUser someUser = new SomeUser();
        someUser.setEmail(registrationRequest.getEmail());
        someUser.setName(registrationRequest.getName());
        someUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        Optional<SomeRole> roleOpt = roleRepository.findByName("ROLE_SOME_USER");
        if (roleOpt.isPresent()) {
            someUser.setRoles(singletonList(roleOpt.get()));
        } else {
            throw new IllegalStateException("Role not found. role: ROLE_SOME_USER");
        }
        return someUser;
    }

    private void clearPasswordData(UserRegistrationRequest registrationRequest) {
        registrationRequest.setPassword(null);
        registrationRequest.setPasswordAgain(null);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
