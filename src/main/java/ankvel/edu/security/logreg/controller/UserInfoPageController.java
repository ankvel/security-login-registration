package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user/info")
public class UserInfoPageController extends BasePageController {

    public UserInfoPageController(UserService userService) {
        super(userService);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String page(Model model) {
        return "userInfo";
    }
}
