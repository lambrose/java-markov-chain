package com.animal.logic.markovChain.exception;

public class InvalidSizeException extends RuntimeException
{
//  Handle 400 invalid integer size request
    public InvalidSizeException(String exception) {
        super(exception);
    }
}