package com.example.exception;

public class RoleNotExist extends Exception {

    public RoleNotExist() {
        super();
    }

    public RoleNotExist(String message) {
        super(message);
    }
}
