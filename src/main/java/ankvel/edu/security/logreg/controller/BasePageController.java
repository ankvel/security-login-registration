package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.service.UserService;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BasePageController {

    private final UserService userService;

    public BasePageController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("currentUser")
    public SomeUser currentUser() {
        return userService.getCurrentUser();
    }

}
