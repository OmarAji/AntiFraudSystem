package antifraud.ip.exception;

public class SuspiciousIpNotFoundException extends RuntimeException {
    public SuspiciousIpNotFoundException(String message) {
        super(message);
    }
}
