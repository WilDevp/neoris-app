package com.neoris.app.domain.exception;

import org.springframework.http.HttpStatus;

public class DomainException extends RuntimeException{
    private HttpStatus httpStatus;

    public DomainException(String message,HttpStatus httpStatus){
       super(message);
       this.httpStatus = httpStatus;
    }
}
