package ankvel.edu.security.logreg.dto;

public class UserVerificationResult {

    private final boolean success;

    public UserVerificationResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
