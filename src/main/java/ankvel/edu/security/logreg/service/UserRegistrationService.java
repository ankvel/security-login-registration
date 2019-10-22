package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.dto.UserRegistrationData;

public interface UserRegistrationService {

    SomeUser registerUser(UserRegistrationData userRegistrationData);
}
