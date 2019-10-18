package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeRole;
import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import ankvel.edu.security.logreg.dto.UserRegistrationRequest;
import ankvel.edu.security.logreg.dto.UserVerificationValidationResult;
import ankvel.edu.security.logreg.repository.RoleRepository;
import ankvel.edu.security.logreg.repository.UserRepository;
import ankvel.edu.security.logreg.repository.UserVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Collections.singletonList;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {


    private final UserVerificationRepository userVerificationRepository;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegistrationServiceImpl(
            UserVerificationRepository userVerificationRepository,
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userVerificationRepository = userVerificationRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SomeUser registerUser(UserRegistrationRequest registrationRequest) {
        SomeUser user = createUserFromRegistrationRequest(registrationRequest);
        SomeUser savedUser = userRepository.save(user);

        // TODO notify app
        createVerification(savedUser);
        return savedUser;
    }

    @Override
    public UserVerification createVerification(SomeUser user) {
        String token = UUID.randomUUID().toString();
        UserVerification result = new UserVerification(token, user);
        userVerificationRepository.save(result);
        return result;
    }

    @Override
    public UserVerificationValidationResult verifyUser(String token) {
        UserVerification userVerification = userVerificationRepository.findByToken(token);
        if (userVerification == null) {
            return new UserVerificationValidationResult(false);
        } else {
            userVerificationRepository.delete(userVerification);
            return new UserVerificationValidationResult(true);
        }
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
}
