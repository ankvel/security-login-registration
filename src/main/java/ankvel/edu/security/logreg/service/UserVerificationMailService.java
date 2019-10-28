package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;

import java.util.Locale;

public interface UserVerificationMailService {
    void sendVerificationTokenEmail(SomeUser user, String token, Locale locale);
}
