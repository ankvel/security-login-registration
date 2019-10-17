package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BasePageController {

    @Autowired
    private UserService userService;

    @ModelAttribute("currentUser")
    public SomeUser currentUser() {
        return userService.getCurrentUser();
    }

}
