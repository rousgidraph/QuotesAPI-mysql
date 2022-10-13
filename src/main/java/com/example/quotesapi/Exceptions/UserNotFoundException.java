package com.example.quotesapi.Exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public UserNotFoundException() {
        super("That User was not found");
    }
}
