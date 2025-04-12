package com.app.jkko.infraestructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class JIKKOException extends RuntimeException{
    private final ErrorResponse detail;
    private final HttpStatus code;

    public JIKKOException(String messageInput, HttpStatus code){
        this.detail = ErrorResponse.builder()
                .message(messageInput)
                .build();
        this.code = code;
    }
}
