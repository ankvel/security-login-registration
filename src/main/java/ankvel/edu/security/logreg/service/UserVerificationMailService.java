package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.UserVerification;

public interface UserVerificationMailService {
    void sendVerificationTokenEmail(UserVerification userVerification);

    void sendAsyncVerificationTokenEmail(UserVerification userVerification);
}
