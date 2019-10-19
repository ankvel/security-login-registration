package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.dto.MessagesData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static java.util.Arrays.asList;

@Controller
@RequestMapping(value = "/login")
public class LoginPageController extends BasePageController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String page(Model model) {

        model.addAttribute("loginInfo",
                new MessagesData(MessagesData.Type.INFO, "You can use", asList(
                        "some1@gmail.com/some1", "some2@gmail.com/some2", "some3@gmail.com/some3"))
        );
        return "login";
    }
}
