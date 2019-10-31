package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.dto.MessagesData;
import ankvel.edu.security.logreg.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

import static java.util.Arrays.asList;

@Controller
@RequestMapping(value = "/login")
public class LoginPageController extends BasePageController {

    private final MessageSource messageSource;

    public LoginPageController(
            MessageSource messageSource,
            UserService userService) {
        super(userService);
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String page(Model model) {

        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("page.login.you.can.use", null, locale);
        model.addAttribute("loginInfo",
                new MessagesData(MessagesData.Type.INFO, message, asList(
                        "some1@gmail.com/some1", "some2@gmail.com/some2", "some3@gmail.com/some3"))
        );
        return "login";
    }
}
