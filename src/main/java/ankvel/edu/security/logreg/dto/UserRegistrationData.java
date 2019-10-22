package ankvel.edu.security.logreg.dto;

import java.util.Locale;
import java.util.TimeZone;

public class UserRegistrationData {
    private final Locale locale;
    private final TimeZone timeZone;
    private final UserRegistrationRequest userRegistrationRequest;

    public UserRegistrationData(UserRegistrationRequest userRegistrationRequest, Locale locale, TimeZone timeZone) {
        this.userRegistrationRequest = userRegistrationRequest;
        this.locale = locale;
        this.timeZone = timeZone;
    }

    public Locale getLocale() {
        return locale;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public UserRegistrationRequest getUserRegistrationRequest() {
        return userRegistrationRequest;
    }
}
