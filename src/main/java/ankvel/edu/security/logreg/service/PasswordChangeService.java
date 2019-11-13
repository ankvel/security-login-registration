package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import ankvel.edu.security.logreg.dto.PasswordChangeResult;

public interface PasswordChangeService {

    UserVerification createPasswordResetVerification(SomeUser user);

    PasswordChangeResult changePassword(String token, String password);

}
