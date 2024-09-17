package antifraud.user.exception;

public class RoleAlreadyAssignException extends RuntimeException {
    public RoleAlreadyAssignException(String message) {
        super(message);
    }
}
