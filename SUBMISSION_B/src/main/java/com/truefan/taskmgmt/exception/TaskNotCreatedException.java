package com.truefan.taskmgmt.exception;

public class TaskNotCreatedException extends Exception {
    public TaskNotCreatedException() {
        super();
    }

    public TaskNotCreatedException(String message) {
        super(message);
    }

    public TaskNotCreatedException(String message, Throwable err) {
        super(message, err);
    }
}
