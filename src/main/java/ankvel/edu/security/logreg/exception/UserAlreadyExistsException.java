package ankvel.edu.security.logreg.exception;

public class UserAlreadyExistsException extends RuntimeException {

    private String email;

    public UserAlreadyExistsException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
