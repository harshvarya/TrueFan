package com.truefan.taskmgmt.exception;

public class UserAlreadyCreatedException extends Exception {
    public UserAlreadyCreatedException() {
        super();
    }

    public UserAlreadyCreatedException(String message) {
        super(message);
    }

    public UserAlreadyCreatedException(String message, Throwable err) {
        super(message, err);
    }
}
