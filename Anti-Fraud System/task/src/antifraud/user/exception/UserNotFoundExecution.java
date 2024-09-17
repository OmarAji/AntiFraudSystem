package antifraud.user.exception;

public class UserNotFoundExecution extends RuntimeException {
    public UserNotFoundExecution(String message) {
        super(message);
    }
}
