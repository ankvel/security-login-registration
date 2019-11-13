package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import ankvel.edu.security.logreg.dto.UserVerificationResult;

public interface UserVerificationService {

    UserVerification createVerification(SomeUser user);

    UserVerificationResult verifyUser(String token);
}
