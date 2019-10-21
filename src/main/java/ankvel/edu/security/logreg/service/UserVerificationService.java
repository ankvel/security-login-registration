package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import ankvel.edu.security.logreg.dto.UserVerificationValidationResult;

public interface UserVerificationService {

    UserVerification createVerification(SomeUser user);

    UserVerificationValidationResult verifyUser(String token);
}
