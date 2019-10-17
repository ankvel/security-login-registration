package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeRole;
import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.dto.UserRegistrationRequest;
import ankvel.edu.security.logreg.repository.RoleRepository;
import ankvel.edu.security.logreg.repository.UserRepository;
import ankvel.edu.security.logreg.repository.UserVerifyTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Collections.singletonList;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {


    private final UserVerifyTokenRepository userVerifyTokenRepository;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegistrationServiceImpl(
            UserVerifyTokenRepository userVerifyTokenRepository,
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userVerifyTokenRepository = userVerifyTokenRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SomeUser registerUser(UserRegistrationRequest registrationRequest) {
        SomeUser user = createUserFromRegistrationRequest(registrationRequest);
        SomeUser savedUser = userRepository.save(user);


        // TODO notify app
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
}
