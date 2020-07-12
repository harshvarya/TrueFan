package com.truefan.taskmgmt.exception;

public class UserNotCreatedException extends Exception {
    public UserNotCreatedException() {
        super();
    }

    public UserNotCreatedException(String message) {
        super(message);
    }

    public UserNotCreatedException(String message, Throwable err) {
        super(message, err);
    }
}
