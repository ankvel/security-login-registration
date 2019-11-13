package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import ankvel.edu.security.logreg.dto.UserVerificationResult;
import ankvel.edu.security.logreg.repository.UserRepository;
import ankvel.edu.security.logreg.repository.UserVerificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserVerificationServiceImpl implements UserVerificationService {

    private final UserVerificationRepository userVerificationRepository;

    private final UserRepository userRepository;

    public UserVerificationServiceImpl(
            UserVerificationRepository userVerificationRepository,
            UserRepository userRepository) {
        this.userVerificationRepository = userVerificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserVerification createVerification(SomeUser user) {
        String token = UUID.randomUUID().toString();
        UserVerification result = new UserVerification(UserVerification.Type.REGISTRATION, token, user);
        userVerificationRepository.save(result);
        return result;
    }

    @Override
    @Transactional
    public UserVerificationResult verifyUser(String token) {

        Optional<UserVerification> userVerificationOpt = userVerificationRepository.findByTokenAndType(
                token, UserVerification.Type.REGISTRATION);
        if (userVerificationOpt.isPresent()) {
            UserVerification userVerification = userVerificationOpt.get();
            SomeUser user = userVerification.getUser();
            user.setEnabled(true);
            userRepository.save(user);
            userVerificationRepository.delete(userVerificationOpt.get());
            return new UserVerificationResult(true);
        } else {
            return new UserVerificationResult(false);
        }
    }
}
