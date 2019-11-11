package ankvel.edu.security.logreg.service;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    private SimpleMailMessage constructEmailMessage(UserVerification userVerification) {
        SomeUser user = userVerification.getUser();
        String token = userVerification.getToken();
        String recipientAddress = user.getEmail();
        String subject = userVerification.getType().toString() + " Confirmation";
        String confirmationUrl = appBaseUrl + "/user/verification/" + token;
        String message = "Please complete your registration by following the confirmation URL";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(from);
        return email;
    }
}
