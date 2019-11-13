package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserVerificationMailServiceImpl implements UserVerificationMailService {

    private final JavaMailSender mailSender;

    private final MessageSource messageSource;

    private final String from;

    private final String appBaseUrl;

    public UserVerificationMailServiceImpl(
            JavaMailSender mailSender,
            MessageSource messageSource,
            @Value("${some.app.support.email}") String from,
            @Value("${some.app.base.url}") String appBaseUrl) {
        this.mailSender = mailSender;
        this.messageSource = messageSource;
        this.from = from;
        this.appBaseUrl = appBaseUrl;
    }

    @Override
    public void sendVerificationTokenEmail(UserVerification userVerification) {
        SimpleMailMessage simpleMailMessage = constructEmailMessage(userVerification);
        mailSender.send(simpleMailMessage);
    }

    @Override
    @Async
    public void sendAsyncVerificationTokenEmail(UserVerification userVerification) {
        SimpleMailMessage simpleMailMessage = constructEmailMessage(userVerification);
        mailSender.send(simpleMailMessage);
    }

    private SimpleMailMessage constructEmailMessage(UserVerification userVerification) {
        SomeUser user = userVerification.getUser();
        String token = userVerification.getToken();
        String recipientAddress = user.getEmail();
        String subject = getSubject(userVerification);
        String confirmationUrl = appBaseUrl + getUrlPart(userVerification) + token;
        String message = getMessage(userVerification);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(from);
        return email;
    }

    private String getSubject(UserVerification userVerification) {
        switch (userVerification.getType()) {
            case REGISTRATION:
                return "Registration Confirmation";
            case PASSWORD_RESET:
                return "Password Reset Confirmation";
            default:
                throw new IllegalArgumentException("illegal userVerification.type");
        }
    }

    private String getUrlPart(UserVerification userVerification) {
        switch (userVerification.getType()) {
            case REGISTRATION:
                return "/user/verification/";
            case PASSWORD_RESET:
                return "/password-reset/";
            default:
                throw new IllegalArgumentException("illegal userVerification.type");
        }
    }

    private String getMessage(UserVerification userVerification) {
        switch (userVerification.getType()) {
            case REGISTRATION:
                return "Please complete your registration by following the confirmation URL";
            case PASSWORD_RESET:
                return "For changing your password please follow the URL";
            default:
                throw new IllegalArgumentException("illegal userVerification.type");
        }
    }
}
