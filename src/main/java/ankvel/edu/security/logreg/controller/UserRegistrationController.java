package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.dto.UserRegistrationRequest;
import ankvel.edu.security.logreg.dto.UserVerificationValidationResult;
import ankvel.edu.security.logreg.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user/registration")
public class UserRegistrationController extends BasePageController {

    private final UserRegistrationService userRegistrationService;

    @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String page() {
        return "userRegistration";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String register(@ModelAttribute("userRegistrationRequest") @Valid UserRegistrationRequest userRegistrationRequest) {
        userRegistrationService.registerUser(userRegistrationRequest);
        return "userRegistrationSuccess";
    }

    @RequestMapping(value = "/verify/{token}", method = RequestMethod.GET)
    @ResponseBody
    public UserVerificationValidationResult verifyUser(@PathVariable String token) {
        return userRegistrationService.verifyUser(token);
    }
}
