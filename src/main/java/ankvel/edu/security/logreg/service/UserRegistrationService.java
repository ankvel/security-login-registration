package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import ankvel.edu.security.logreg.dto.UserRegistrationRequest;
import ankvel.edu.security.logreg.dto.UserVerificationValidationResult;

public interface UserRegistrationService {

    SomeUser registerUser(UserRegistrationRequest registrationRequest);

    UserVerification createVerification(SomeUser user);

    UserVerificationValidationResult verifyUser(String token);
}
