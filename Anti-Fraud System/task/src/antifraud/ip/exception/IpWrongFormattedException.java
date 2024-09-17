package antifraud.ip.exception;

public class IpWrongFormattedException extends RuntimeException {
    public IpWrongFormattedException(String message) {
        super(message);
    }
}
