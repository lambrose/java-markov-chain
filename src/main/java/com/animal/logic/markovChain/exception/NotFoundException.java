package com.animal.logic.markovChain.exception;

public class NotFoundException extends RuntimeException
{
//  Handle 404 missing field in request
    public NotFoundException(String exception) {
        super(exception);
    }
}
