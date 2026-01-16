package com.example.demo.exception;

import java.time.OffsetDateTime;
import java.util.List;

public class ApiErrorDto {
    private final OffsetDateTime timestamp = OffsetDateTime.now();
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final List<FieldErrorDto> fieldErrors;

    public ApiErrorDto(int status, String error, String message, String path, List<FieldErrorDto> fieldErrors) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fieldErrors = fieldErrors;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public List<FieldErrorDto> getFieldErrors() {
        return fieldErrors;
    }
}