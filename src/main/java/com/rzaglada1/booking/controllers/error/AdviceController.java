package com.rzaglada1.booking.controllers.error;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.NoSuchElementException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validationException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> noSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
    }


    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalMonitorStateException.class})
    public ResponseEntity<Object> illegalArgument(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }

//    @ExceptionHandler(value = HttpClientErrorException.class)
//    public ResponseEntity<Object> httpClientError(HttpClientErrorException e) {
//        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
//    }

}

