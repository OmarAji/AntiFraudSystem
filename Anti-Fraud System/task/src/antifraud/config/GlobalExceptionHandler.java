package antifraud.config;

import antifraud.card.exception.CardNotFoundException;
import antifraud.card.exception.InvalidCardNumberException;
import antifraud.card.exception.StolenCardAlreadyExistException;
import antifraud.ip.exception.IpWrongFormattedException;
import antifraud.ip.exception.SuspiciousIpAlreadyExistException;
import antifraud.ip.exception.SuspiciousIpNotFoundException;
import antifraud.transaction.exception.FeedbackFormatException;
import antifraud.transaction.exception.TransactionAlreadyExistException;
import antifraud.transaction.exception.TransactionNotFoundException;
import antifraud.transaction.exception.UnprocessableException;
import antifraud.user.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleRegistrationException(RegistrationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }


    @ExceptionHandler(NotAllowedRoleException.class)
    public ResponseEntity<String> handleChangeRoleException(NotAllowedRoleException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserCanNotLockedException.class)
    public ResponseEntity<String> handleUserCanNotLockedException(UserCanNotLockedException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }


    @ExceptionHandler(RoleAlreadyAssignException.class)
    public ResponseEntity<String> handleRoleAlreadyAssignException(RoleAlreadyAssignException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundExecution.class)
    public ResponseEntity<String> handleUserNotFoundExecution(UserNotFoundExecution ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserLockedException.class)
    public ResponseEntity<String> handleUserLockedException(UserLockedException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }

    @ExceptionHandler(SuspiciousIpAlreadyExistException.class)
    public ResponseEntity<String> handleSuspiciousIpAlreadyExistException(SuspiciousIpAlreadyExistException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }


    @ExceptionHandler(IpWrongFormattedException.class)
    public ResponseEntity<String> handleIpWrongFormatedException(IpWrongFormattedException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }


    @ExceptionHandler(SuspiciousIpNotFoundException.class)
    public ResponseEntity<String> handleSuspiciousIpNotFoundException(SuspiciousIpNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }


    @ExceptionHandler(InvalidCardNumberException.class)
    public ResponseEntity<String> handleInvalidCardNumberException(InvalidCardNumberException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<String> handleCardNotFoundException(CardNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(StolenCardAlreadyExistException.class)
    public ResponseEntity<String> handleStolenCardAlreadyExistException(StolenCardAlreadyExistException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }


    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<String> handleTransactionNotFoundException(TransactionNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }


    @ExceptionHandler(TransactionAlreadyExistException.class)
    public ResponseEntity<String> handleTransactionAlreadyExistException(TransactionAlreadyExistException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }


    @ExceptionHandler(FeedbackFormatException.class)
    public ResponseEntity<String> handleFeedbackFormatException(FeedbackFormatException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UnprocessableException.class)
    public ResponseEntity<String> handleUnprocessableException(UnprocessableException ex) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ex.getMessage());
    }


}
