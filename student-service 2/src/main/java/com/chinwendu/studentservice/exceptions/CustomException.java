package com.chinwendu.studentservice.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

    private HttpStatus httpStatus;
    public CustomException() {
    }

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
