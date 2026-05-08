package com.max.MyProject.exceptions;

public abstract class BaseProjectException extends RuntimeException {
    public BaseProjectException(String message) {
        super(message);
    }
}
