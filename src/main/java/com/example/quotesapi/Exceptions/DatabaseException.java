package com.example.quotesapi.Exceptions;

public class DatabaseException extends Exception{
    public DatabaseException(String errorMessage) {
        super(errorMessage);
    }

    public DatabaseException() {
        super("Something went wrong accessing the database");
    }
}
