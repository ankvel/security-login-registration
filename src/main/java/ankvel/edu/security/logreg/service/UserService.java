package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerifyToken;

public interface UserService {

    SomeUser getCurrentUser();

    UserVerifyToken createVerifyToken(SomeUser user);
}
