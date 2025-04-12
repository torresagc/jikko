package com.app.jkko.infraestructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(
            final Exception exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(ErrorMessage.NOT_CHECK).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JIKKOException.class)
    public ResponseEntity<ErrorResponse> nisumExceptionHandler(
            final JIKKOException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getDetail(), exception.getCode());
    }

}
