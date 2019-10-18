package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;

public interface UserService {

    SomeUser getCurrentUser();

    UserVerification createVerificationToken(SomeUser user);
}
