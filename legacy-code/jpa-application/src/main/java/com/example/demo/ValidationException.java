package com.example.demo;

/**
 * Created by Daniel on 2017. 7. 9..
 */
public class ValidationException extends RuntimeException {
    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }
}
