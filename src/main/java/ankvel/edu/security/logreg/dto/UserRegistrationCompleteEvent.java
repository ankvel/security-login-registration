package ankvel.edu.security.logreg.dto;

import ankvel.edu.security.logreg.domain.SomeUser;

public class UserRegistrationCompleteEvent {
    private final SomeUser user;

    public UserRegistrationCompleteEvent(SomeUser user) {
        this.user = user;
    }

    public SomeUser getUser() {
        return user;
    }
}
