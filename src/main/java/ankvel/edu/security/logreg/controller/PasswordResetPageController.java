package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.domain.UserVerification;
import ankvel.edu.security.logreg.dto.PasswordChangeByTokenRequest;
import ankvel.edu.security.logreg.dto.PasswordChangeResult;
import ankvel.edu.security.logreg.dto.PasswordResetVerificationRequest;
import ankvel.edu.security.logreg.service.PasswordChangeService;
import ankvel.edu.security.logreg.service.UserService;
import ankvel.edu.security.logreg.service.UserVerificationMailService;
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
@RequestMapping(value = "/password-reset")
public class PasswordResetPageController extends BasePageController {

    private final PasswordChangeService passwordChangeService;

    private final UserService userService;

    private final UserVerificationMailService userVerificationMailService;

    public PasswordResetPageController(
            UserService userService,
            PasswordChangeService passwordChangeService,
            UserVerificationMailService userVerificationMailService) {
        super(userService);
        this.userService = userService;
        this.passwordChangeService = passwordChangeService;
        this.userVerificationMailService = userVerificationMailService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String verificationPage(
            @ModelAttribute("passwordResetVerificationRequest")
                    PasswordResetVerificationRequest passwordResetVerificationRequest
    ) {
        return "passwordReset";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String sendVerification(
            @ModelAttribute("passwordResetVerificationRequest") @Valid
                    PasswordResetVerificationRequest passwordResetVerificationRequest,
            BindingResult bindingResult,
            Model model) {

        if (!bindingResult.hasErrors()) {
            boolean sendResult = sendVerification(passwordResetVerificationRequest);
            model.addAttribute("sendResult", sendResult);
        }
        return "passwordReset";
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public String changePasswordPage(
            @PathVariable("token") String token,
            @ModelAttribute("passwordChangeByTokenRequest") PasswordChangeByTokenRequest passwordChangeByTokenRequest
    ) {
        passwordChangeByTokenRequest.setToken(token);
        return "passwordChangeByToken";
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.POST)
    public String changePasswordByToken(
            @PathVariable("token") String token,
            @ModelAttribute("passwordChangeByTokenRequest") @Valid PasswordChangeByTokenRequest passwordChangeByTokenRequest,
            BindingResult bindingResult,
            Model model
    ) {
        if (!bindingResult.hasErrors()) {
            String requestToken = passwordChangeByTokenRequest.getToken();
            String password = passwordChangeByTokenRequest.getPassword();
            PasswordChangeResult passwordChangeResult = passwordChangeService.changePassword(requestToken, password);
            if (passwordChangeResult.isSuccess()) {
                model.addAttribute("passwordChanged", true);
            }
        }
        return "passwordChangeByToken";
    }

    private boolean sendVerification(PasswordResetVerificationRequest verificationRequest) {
        String email = verificationRequest.getEmail();
        Optional<SomeUser> userOpt = userService.findByEmail(email);
        if (userOpt.isPresent()) {
            SomeUser user = userOpt.get();
            UserVerification verification = passwordChangeService.createPasswordResetVerification(user);
            userVerificationMailService.sendAsyncVerificationTokenEmail(verification);
            return true;
        } else {
            return false;
        }
    }
}
