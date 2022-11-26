package com.animal.logic.markovChain.service;

import com.animal.logic.markovChain.exception.InvalidInputException;
import com.animal.logic.markovChain.exception.InvalidSizeException;
import com.animal.logic.markovChain.exception.NotFoundException;

public class ExceptionService {

//    Throw client-side error
    public static void ExceptionHandler(Object value) {
        if(value == null) throw new NotFoundException("Missing request body field");
        else if(value instanceof Integer) throw new InvalidSizeException("Invalid size value");
        else throw new InvalidInputException("Invalid field data type");
    }
}
