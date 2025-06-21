package com.springboot.crud.exception;

public class NoSuchUserExistException extends RuntimeException {
    private String message;

    public NoSuchUserExistException() {
    }

    public NoSuchUserExistException(String message) {
        super(message);
        this.message = message;
    }
}
