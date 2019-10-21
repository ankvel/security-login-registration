package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.dto.UserRegistrationRequest;

public interface UserRegistrationService {

    SomeUser registerUser(UserRegistrationRequest registrationRequest);


}
