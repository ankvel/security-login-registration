package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.dto.MessagesData;
import ankvel.edu.security.logreg.dto.UserRegistrationCompleteEvent;
import ankvel.edu.security.logreg.dto.UserRegistrationData;
import ankvel.edu.security.logreg.dto.UserRegistrationRequest;
import ankvel.edu.security.logreg.exception.UserAlreadyExistsException;
import ankvel.edu.security.logreg.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.TimeZone;

import static java.util.Collections.singletonList;

@Controller
@RequestMapping("/user/registration")
public class UserRegistrationController extends BasePageController {

    private final UserRegistrationService userRegistrationService;

    private final ApplicationEventPublisher eventPublisher;


    @Autowired
    public UserRegistrationController(
            UserRegistrationService userRegistrationService,
            ApplicationEventPublisher eventPublisher) {
        this.userRegistrationService = userRegistrationService;
        this.eventPublisher = eventPublisher;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String page() {
        return "userRegistration";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String register(
            HttpServletRequest httpServletRequest,
            @ModelAttribute("userRegistrationRequest") @Valid UserRegistrationRequest userRegistrationRequest,
            BindingResult bindingResult,
            Model model) {
        try {
            UserRegistrationData userRegistrationData = getUserRegistrationData(userRegistrationRequest, httpServletRequest);
            SomeUser user = userRegistrationService.registerUser(userRegistrationData);
            eventPublisher.publishEvent(new UserRegistrationCompleteEvent(user, userRegistrationData));
        } catch (UserAlreadyExistsException ex) {
            return handleUserAlreadyExists(ex, model);
        }
        if (bindingResult.hasErrors()) {
            return "userRegistration";
        }
        return "userRegistrationSuccess";
    }

    private String handleUserAlreadyExists(UserAlreadyExistsException ex, Model model) {

        String email = ex.getEmail();
        MessagesData registrationErrors = new MessagesData(
                MessagesData.Type.ERROR, "Registration errors", singletonList("User already exists " + email));
        model.addAttribute("registrationErrors", registrationErrors);
        return "userRegistration";
    }

    private UserRegistrationData getUserRegistrationData(
            UserRegistrationRequest userRegistrationRequest,
            HttpServletRequest httpServletRequest) {

        Locale locale = RequestContextUtils.getLocale(httpServletRequest);
        TimeZone timeZone = RequestContextUtils.getTimeZone(httpServletRequest);
        return new UserRegistrationData(userRegistrationRequest, locale, timeZone);
    }
}
