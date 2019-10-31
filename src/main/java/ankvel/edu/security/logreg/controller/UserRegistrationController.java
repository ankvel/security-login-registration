package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.dto.UserRegistrationCompleteEvent;
import ankvel.edu.security.logreg.dto.UserRegistrationData;
import ankvel.edu.security.logreg.dto.UserRegistrationRequest;
import ankvel.edu.security.logreg.exception.UserAlreadyExistsException;
import ankvel.edu.security.logreg.service.UserRegistrationService;
import ankvel.edu.security.logreg.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.TimeZone;

@Controller
@RequestMapping("/user/registration")
public class UserRegistrationController extends BasePageController {

    private final UserRegistrationService userRegistrationService;

    private final ApplicationEventPublisher eventPublisher;

    private final MessageSource messageSource;

    public UserRegistrationController(
            UserRegistrationService userRegistrationService,
            ApplicationEventPublisher eventPublisher,
            MessageSource messageSource,
            UserService userService) {
        super(userService);
        this.userRegistrationService = userRegistrationService;
        this.eventPublisher = eventPublisher;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String page(
            @ModelAttribute("userRegistrationRequest") UserRegistrationRequest userRegistrationRequest) {
        return "userRegistration";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String register(
            HttpServletRequest httpServletRequest,
            @ModelAttribute("userRegistrationRequest") @Valid UserRegistrationRequest userRegistrationRequest,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "userRegistration";
        }
        try {
            UserRegistrationData userRegistrationData = getUserRegistrationData(userRegistrationRequest, httpServletRequest);
            SomeUser user = userRegistrationService.registerUser(userRegistrationData);
            eventPublisher.publishEvent(new UserRegistrationCompleteEvent(user, userRegistrationData));
        } catch (UserAlreadyExistsException ex) {
            handleUserAlreadyExists(ex, bindingResult);
            return "userRegistration";
        }
        return "userRegistrationSuccess";
    }

    private void handleUserAlreadyExists(UserAlreadyExistsException ex, BindingResult bindingResult) {

        Locale locale = LocaleContextHolder.getLocale();
        String email = ex.getEmail();
        String userAlreadyExistsMessage = messageSource.getMessage(
                "error.user.registration.already.exists", new String[]{email}, locale);
        bindingResult.addError(new FieldError("userRegistrationRequest", "email", userAlreadyExistsMessage));
    }

    private UserRegistrationData getUserRegistrationData(
            UserRegistrationRequest userRegistrationRequest,
            HttpServletRequest httpServletRequest) {

        TimeZone timeZone = RequestContextUtils.getTimeZone(httpServletRequest);
        return new UserRegistrationData(userRegistrationRequest, LocaleContextHolder.getLocale(), timeZone);
    }
}
