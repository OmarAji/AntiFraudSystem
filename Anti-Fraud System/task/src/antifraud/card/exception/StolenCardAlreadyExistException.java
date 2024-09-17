package antifraud.card.exception;

public class StolenCardAlreadyExistException extends RuntimeException {
    public StolenCardAlreadyExistException(String message) {
        super(message);
    }
}
