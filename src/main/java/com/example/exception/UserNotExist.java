package com.example.exception;

public class UserNotExist extends Exception {
    public UserNotExist() {
        super();
    }

    public UserNotExist(String message) {
        super(message);
    }
}
