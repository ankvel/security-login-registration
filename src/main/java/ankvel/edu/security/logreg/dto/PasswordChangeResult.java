package ankvel.edu.security.logreg.dto;

public class PasswordChangeResult {

    private final boolean success;

    public PasswordChangeResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
