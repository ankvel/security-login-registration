package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.dto.MessagesData;
import ankvel.edu.security.logreg.dto.UserRegistrationRequest;
import ankvel.edu.security.logreg.dto.UserVerificationValidationResult;
import ankvel.edu.security.logreg.exception.UserAlreadyExistsException;
import ankvel.edu.security.logreg.service.UserRegistrationService;
import ankvel.edu.security.logreg.service.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Collections.singletonList;

@Controller
@RequestMapping("/user/registration")
public class UserRegistrationController extends BasePageController {

    private final UserRegistrationService userRegistrationService;

    private final UserVerificationService userVerificationService;

    @Autowired
    public UserRegistrationController(
            UserRegistrationService userRegistrationService,
            UserVerificationService userVerificationService) {
        this.userRegistrationService = userRegistrationService;
        this.userVerificationService = userVerificationService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String page() {
        return "userRegistration";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String register(
            @ModelAttribute("userRegistrationRequest") @Valid UserRegistrationRequest userRegistrationRequest,
            BindingResult bindingResult,
            Model model) {
        try {
            userRegistrationService.registerUser(userRegistrationRequest);
        } catch (UserAlreadyExistsException ex) {
            return handleUserAlreadyExists(ex, model);
        }
        if (bindingResult.hasErrors()) {
            return "userRegistration";
        }
        return "userRegistrationSuccess";
    }

    @RequestMapping(value = "/verify/{token}", method = RequestMethod.GET)
    @ResponseBody
    public UserVerificationValidationResult verifyUser(@PathVariable String token) {
        return userVerificationService.verifyUser(token);
    }

    private String handleUserAlreadyExists(UserAlreadyExistsException ex, Model model) {
        String email = ex.getEmail();
        MessagesData registrationErrors = new MessagesData(
                MessagesData.Type.ERROR, "Registration errors", singletonList("User already exists " + email));
        model.addAttribute("registrationErrors", registrationErrors);
        return "userRegistration";
    }
}
