package antifraud.user.exception;

public class NotAllowedRoleException extends RuntimeException {
    public NotAllowedRoleException(String message) {
        super(message);
    }
}
