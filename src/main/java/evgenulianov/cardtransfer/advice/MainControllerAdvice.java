package evgenulianov.cardtransfer.advice;

import evgenulianov.cardtransfer.requests.ResponceTransfer;
import evgenulianov.cardtransfer.requests.ResponceTransferUnsuccessful;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Log4j2
public class MainControllerAdvice {


    @AliasFor("evgenulianov.cardtransfer.advice")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponceTransfer> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        log.error(exception.getMessage());
        String message = "NOT VALIDATED! " + exception.getLocalizedMessage();
        ResponceTransfer responce = new ResponceTransferUnsuccessful(message, 400);
        ResponseEntity<ResponceTransfer> responseEntity = new ResponseEntity<ResponceTransfer>(responce, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @AliasFor("evgenulianov.cardtransfer.advice")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponceTransfer> constraintViolationExceptionHandler(ConstraintViolationException exception){
        log.error(exception.getMessage());
        String message = "NOT VALIDATED! " + exception.getLocalizedMessage();
        ResponceTransfer responce = new ResponceTransferUnsuccessful(message, 400);
        ResponseEntity<ResponceTransfer> responseEntity = new ResponseEntity<ResponceTransfer>(responce, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @AliasFor("evgenulianov.cardtransfer.advice")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponceTransfer> notFoundExceptionHandler(NotFoundException exception){
        log.error(exception.getMessage());
        String message = "NOT FOUND! " + exception.getLocalizedMessage();
        ResponceTransfer responce = new ResponceTransferUnsuccessful(message, 404);
        ResponseEntity<ResponceTransfer> responseEntity = new ResponseEntity<ResponceTransfer>(responce, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @AliasFor("evgenulianov.cardtransfer.advice")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponceTransfer> runtimeExceptionHandler(RuntimeException exception){
        log.error(exception.getMessage());
        String message = exception.getLocalizedMessage();
        ResponceTransfer responce = new ResponceTransferUnsuccessful(message, 500);
        ResponseEntity<ResponceTransfer> responseEntity = new ResponseEntity<ResponceTransfer>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

}
