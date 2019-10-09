package ankvel.edu.security.logreg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/home")
public class HomePageController extends BasePageController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String page(Model model) {
        return "home";
    }
}
