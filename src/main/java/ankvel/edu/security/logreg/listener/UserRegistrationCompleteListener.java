package ankvel.edu.security.logreg.listener;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import ankvel.edu.security.logreg.dto.UserRegistrationCompleteEvent;
import ankvel.edu.security.logreg.service.UserVerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationCompleteListener {

    private static final Logger log = LoggerFactory.getLogger(UserRegistrationCompleteListener.class);

    private final UserVerificationService userVerificationService;

    private final JavaMailSender mailSender;

    private final String from;

    private final String appBaseUrl;

    public UserRegistrationCompleteListener(
            UserVerificationService userVerificationService,
            JavaMailSender mailSender,
            @Value("${some.app.support.email}") String from,
            @Value("${some.app.base.url}") String appBaseUrl) {
        this.userVerificationService = userVerificationService;
        this.mailSender = mailSender;
        this.from = from;
        this.appBaseUrl = appBaseUrl;
    }

    @Async
    @EventListener
    public void onRegistrationComplete(UserRegistrationCompleteEvent event) {
        SomeUser user = event.getUser();
        UserVerification userVerification = userVerificationService.createVerification(user);
        SimpleMailMessage simpleMailMessage = constructEmailMessage(event, userVerification);
        mailSender.send(simpleMailMessage);
        log.info("A new user registration complete. user: {}", event.getUser());
    }

    private SimpleMailMessage constructEmailMessage(UserRegistrationCompleteEvent event, UserVerification userVerification) {
        SomeUser user = event.getUser();
        String token = userVerification.getToken();
        // Locale locale = event.getUserRegistrationData().getLocale();

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = appBaseUrl + "/user/verification/confirm/" + token;
        String message = "Please complete your registration by following the confirmation URL";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(from);
        return email;
    }
}
