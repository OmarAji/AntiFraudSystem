package antifraud.ip.exception;

public class SuspiciousIpAlreadyExistException extends RuntimeException {
    public SuspiciousIpAlreadyExistException(String message) {
        super(message);
    }
}
