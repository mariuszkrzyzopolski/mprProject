package pl.pjatk.gameplay.advice;

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
        return new ResponseEntity<>("Element is no longer exist here or wasn't created",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> badAttribute(NoSuchMethodException e){
        return new ResponseEntity<>("Item have a bad attribute - cant use on that object",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomErrorException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> deleted(CustomErrorException e){
        return new ResponseEntity<>(e.getCustomMessage(),HttpStatus.NOT_FOUND);
    }
}
