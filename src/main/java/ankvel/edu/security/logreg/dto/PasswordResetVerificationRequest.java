package ankvel.edu.security.logreg.dto;

import javax.validation.constraints.NotEmpty;

public class PasswordResetVerificationRequest {

    @NotEmpty
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
