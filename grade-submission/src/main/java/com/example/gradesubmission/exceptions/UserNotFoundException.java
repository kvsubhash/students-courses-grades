package com.example.gradesubmission.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long userId) {
        super("user with id: " + userId + " not found!");
    }

    public UserNotFoundException(String username) {
        super("user with username: " + username + " not found!");
    }
    
}
