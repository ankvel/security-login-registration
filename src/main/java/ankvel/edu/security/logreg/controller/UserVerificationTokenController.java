package ankvel.edu.security.logreg.controller;

import ankvel.edu.security.logreg.domain.SomeUser;
import ankvel.edu.security.logreg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserVerificationTokenController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/verification")
    public String verification() {
        SomeUser currentUser = userService.getCurrentUser();
        return userService.createVerificationToken(currentUser).getToken();
    }
}
