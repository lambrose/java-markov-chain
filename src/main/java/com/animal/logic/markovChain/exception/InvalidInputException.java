package com.animal.logic.markovChain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException
{
//  Handle 400 invalid data type request
    public InvalidInputException(String exception) {
        super(exception);
    }
}