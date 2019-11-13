package ankvel.edu.security.logreg.dto;

import javax.validation.constraints.NotEmpty;

public class PasswordChangeByTokenRequest {

    @NotEmpty
    private String password;

    @NotEmpty
    private String passwordAgain;

    private String token;

    public PasswordChangeByTokenRequest() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
