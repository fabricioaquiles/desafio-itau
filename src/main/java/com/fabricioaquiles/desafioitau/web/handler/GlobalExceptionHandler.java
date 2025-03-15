package com.fabricioaquiles.desafioitau.web.handler;

import com.fabricioaquiles.desafioitau.infrastructure.exceptions.UnprocessableEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnprocessableEntity.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Void> handleUnprocessableEntityException(UnprocessableEntity e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Void> handleOtherException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
