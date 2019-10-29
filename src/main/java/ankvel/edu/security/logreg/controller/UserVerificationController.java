package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import ankvel.edu.security.logreg.dto.UserVerificationRequest;
import ankvel.edu.security.logreg.repository.UserRepository;
import ankvel.edu.security.logreg.service.UserVerificationMailService;
import ankvel.edu.security.logreg.service.UserVerificationService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user/verification")
public class UserVerificationController extends BasePageController {

    private final UserVerificationService userVerificationService;

    private final UserVerificationMailService userVerificationMailService;

    private final UserRepository userRepository;

    public UserVerificationController(
            UserVerificationService userVerificationService,
            UserVerificationMailService userVerificationMailService,
            UserRepository userRepository) {
        this.userVerificationService = userVerificationService;
        this.userVerificationMailService = userVerificationMailService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/confirm/{token}", method = RequestMethod.GET)
    public String verifyUser(@PathVariable String token, Model model) {
        model.addAttribute("verificationResult", userVerificationService.verifyUser(token));
        return "userVerificationResult";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String verificationPage() {
        return "userVerification";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String sendVerification(@Valid UserVerificationRequest userVerificationRequest, Model model) {
        String email = userVerificationRequest.getEmail();
        Optional<SomeUser> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            SomeUser user = userOpt.get();
            UserVerification verification = userVerificationService.createVerification(user);
            userVerificationMailService.sendVerificationTokenEmail(
                    user, verification.getToken(), LocaleContextHolder.getLocale());
            model.addAttribute("sendResult", true);
        } else {
            model.addAttribute("sendResult", false);

        }
        return "userVerification";
    }
}
