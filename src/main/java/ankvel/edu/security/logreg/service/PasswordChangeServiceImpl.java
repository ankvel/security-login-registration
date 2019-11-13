package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import ankvel.edu.security.logreg.dto.PasswordChangeResult;
import ankvel.edu.security.logreg.repository.UserRepository;
import ankvel.edu.security.logreg.repository.UserVerificationRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordChangeServiceImpl implements PasswordChangeService {

    private final UserVerificationRepository userVerificationRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public PasswordChangeServiceImpl(
            UserVerificationRepository userVerificationRepository,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository) {
        this.userVerificationRepository = userVerificationRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserVerification createPasswordResetVerification(SomeUser user) {
        String token = UUID.randomUUID().toString();
        UserVerification result = new UserVerification(UserVerification.Type.PASSWORD_RESET, token, user);
        userVerificationRepository.save(result);
        return result;
    }

    @Override
    @Transactional
    public PasswordChangeResult changePassword(String token, String password) {
        Optional<UserVerification> verificationOpt = userVerificationRepository.findByTokenAndType(
                token, UserVerification.Type.PASSWORD_RESET);
        if (verificationOpt.isPresent()) {
            UserVerification verification = verificationOpt.get();
            SomeUser user = verification.getUser();
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            userVerificationRepository.delete(verification);
            return new PasswordChangeResult(true);
        } else {
            return new PasswordChangeResult(false);
        }
    }
}
