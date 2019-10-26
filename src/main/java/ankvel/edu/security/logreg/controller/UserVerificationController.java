package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.service.UserVerificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/verification")
public class UserVerificationController {

    private final UserVerificationService userVerificationService;

    public UserVerificationController(UserVerificationService userVerificationService) {
        this.userVerificationService = userVerificationService;
    }

    @RequestMapping(value = "/confirm/{token}", method = RequestMethod.GET)
    public String verifyUser(@PathVariable String token, Model model) {
        model.addAttribute("verificationResult", userVerificationService.verifyUser(token));
        return "userVerification";
    }
}
