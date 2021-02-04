package pl.pjatk.gameplay.advice;

import org.hibernate.ObjectDeletedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pjatk.gameplay.model.CustomErrorException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> notFound(NoSuchElementException e){
        return new ResponseEntity<>("Element is no longer exist here",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomErrorException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> deleted(CustomErrorException e){
        return new ResponseEntity<>(e.getCustomMessage(),HttpStatus.NOT_FOUND);
    }
}
