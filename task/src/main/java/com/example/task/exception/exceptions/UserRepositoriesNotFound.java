package com.example.task.exception.exceptions;

public class UserRepositoriesNotFound extends Exception {
    public UserRepositoriesNotFound() {
        super("Didn't found repositories for this username");
    }
}
