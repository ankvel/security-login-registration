package ankvel.edu.security.logreg.listener;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import ankvel.edu.security.logreg.dto.UserRegistrationCompleteEvent;
import ankvel.edu.security.logreg.service.UserVerificationMailService;
import ankvel.edu.security.logreg.service.UserVerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationCompleteListener {

    private static final Logger log = LoggerFactory.getLogger(UserRegistrationCompleteListener.class);

    private final UserVerificationService userVerificationService;

    private final UserVerificationMailService userVerificationMailService;

    public UserRegistrationCompleteListener(
            UserVerificationService userVerificationService,
            UserVerificationMailService userVerificationMailService) {
        this.userVerificationService = userVerificationService;
        this.userVerificationMailService = userVerificationMailService;
    }

    @Async
    @EventListener
    public void onRegistrationComplete(UserRegistrationCompleteEvent event) {
        SomeUser user = event.getUser();
        UserVerification userVerification = userVerificationService.createVerification(user);
        userVerificationMailService.sendVerificationTokenEmail(
                user, userVerification.getToken(), event.getUserRegistrationData().getLocale());
        log.info("A new user registration complete. user: {}", event.getUser());
    }
}
