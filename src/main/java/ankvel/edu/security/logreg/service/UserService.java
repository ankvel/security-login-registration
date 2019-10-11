package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.model.SomeUser;
import ankvel.edu.security.logreg.model.UserVerifyToken;

public interface UserService {

    SomeUser getCurrentUser();

    UserVerifyToken createVerifyToken(SomeUser user);
}
