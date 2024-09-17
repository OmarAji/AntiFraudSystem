package antifraud.user.exception;

public class UserCanNotLockedException extends RuntimeException {
    public UserCanNotLockedException(String message) {
        super(message);
    }
}
