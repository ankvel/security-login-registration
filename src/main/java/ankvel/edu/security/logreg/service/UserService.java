package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerificationToken;

public interface UserService {

    SomeUser getCurrentUser();

    UserVerificationToken createVerificationToken(SomeUser user);
}
