package ankvel.edu.security.logreg.dto;

public class UserVerificationValidationResult {

    private final boolean success;

    public UserVerificationValidationResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
