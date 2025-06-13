package com.project.flipzone.exception.customException;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
