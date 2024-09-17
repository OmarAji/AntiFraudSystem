package antifraud.transaction.exception;

public class TransactionAlreadyExistException extends RuntimeException {
    public TransactionAlreadyExistException(String message) {
        super(message);
    }
}
