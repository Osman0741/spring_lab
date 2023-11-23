package com.cydeo.lab08rest.exception;

public class CurrencyInvalidException extends RuntimeException{
    public CurrencyInvalidException(String message){
        super(message);
    }
}
