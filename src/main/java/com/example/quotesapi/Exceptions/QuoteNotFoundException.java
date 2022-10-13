package com.example.quotesapi.Exceptions;

public class QuoteNotFoundException extends Exception {

    public QuoteNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public QuoteNotFoundException() {
        super("That Quote Does not Exist");
    }
}
