package com.example.exception;

public class WrongOldPassword extends Exception {
    public WrongOldPassword() {
        super();
    }

    public WrongOldPassword(String message) {
        super(message);
    }
}
