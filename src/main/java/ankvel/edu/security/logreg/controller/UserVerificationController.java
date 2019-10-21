package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.dto.UserVerificationValidationResult;
import ankvel.edu.security.logreg.service.UserVerificationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/verification")
public class UserVerificationController {

    private final UserVerificationService userVerificationService;

    public UserVerificationController(UserVerificationService userVerificationService) {
        this.userVerificationService = userVerificationService;
    }

    @RequestMapping(value = "/confirm/{token}", method = RequestMethod.GET)
    @ResponseBody
    public UserVerificationValidationResult verifyUser(@PathVariable String token) {
        return userVerificationService.verifyUser(token);
    }
}
