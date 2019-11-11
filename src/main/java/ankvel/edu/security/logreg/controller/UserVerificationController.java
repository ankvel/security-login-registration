package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import ankvel.edu.security.logreg.dto.UserVerificationRequest;
import ankvel.edu.security.logreg.repository.UserRepository;
import ankvel.edu.security.logreg.service.UserService;
import ankvel.edu.security.logreg.service.UserVerificationMailService;
import ankvel.edu.security.logreg.service.UserVerificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
            UserRepository userRepository,
            UserService userService) {
        super(userService);
        this.userVerificationService = userVerificationService;
        this.userVerificationMailService = userVerificationMailService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public String verifyUser(@PathVariable String token, Model model) {
        model.addAttribute("verificationResult", userVerificationService.verifyUser(token));
        return "userVerificationResult";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String verificationPage(
            @ModelAttribute("userVerificationRequest") UserVerificationRequest userVerificationRequest
    ) {
        return "userVerification";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String sendVerification(
            @ModelAttribute("userVerificationRequest") @Valid UserVerificationRequest userVerificationRequest,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "userVerification";
        }
        String email = userVerificationRequest.getEmail();
        Optional<SomeUser> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            SomeUser user = userOpt.get();
            UserVerification verification = userVerificationService.createVerification(user);
            userVerificationMailService.sendVerificationTokenEmail(verification);
            model.addAttribute("sendResult", true);
        } else {
            model.addAttribute("sendResult", false);

        }
        return "userVerification";
    }
}
