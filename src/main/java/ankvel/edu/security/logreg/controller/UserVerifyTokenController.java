package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.model.SomeUser;
import ankvel.edu.security.logreg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserVerifyTokenController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/verify")
    public String verify() {
        SomeUser currentUser = userService.getCurrentUser();
        return userService.createVerifyToken(currentUser).getToken();
    }
}
