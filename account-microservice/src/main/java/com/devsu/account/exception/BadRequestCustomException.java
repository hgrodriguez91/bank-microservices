package com.devsu.account.exception;

public class BadRequestCustomException extends RuntimeException {
    public BadRequestCustomException(String message) {
        super(message);
    }
}
