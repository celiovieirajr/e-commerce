package com.example.demo.modules.viaceps.exception;

import org.springframework.http.HttpStatus;

public class ViacepException extends RuntimeException {

    private final HttpStatus status;

    public ViacepException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

