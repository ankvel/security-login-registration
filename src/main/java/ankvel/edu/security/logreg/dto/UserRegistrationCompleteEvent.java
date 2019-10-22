package ankvel.edu.security.logreg.dto;

import ankvel.edu.security.logreg.domain.SomeUser;

public class UserRegistrationCompleteEvent {
    private final SomeUser user;
    private final UserRegistrationData userRegistrationData;

    public UserRegistrationCompleteEvent(SomeUser user, UserRegistrationData userRegistrationData) {
        this.user = user;
        this.userRegistrationData = userRegistrationData;
    }

    public SomeUser getUser() {
        return user;
    }

    public UserRegistrationData getUserRegistrationData() {
        return userRegistrationData;
    }
}
