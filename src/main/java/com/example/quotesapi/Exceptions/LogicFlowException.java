package com.example.quotesapi.Exceptions;

public class LogicFlowException extends Exception{
    public LogicFlowException(String errorMessage) {
        super(errorMessage);
    }

    public LogicFlowException() {
        super("The action you are attempting breaks the application logic flow. 💀");
    }
}
