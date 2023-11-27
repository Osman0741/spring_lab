package com.cydeo.lab08rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class,RuntimeException.class, Throwable.class})
    public ResponseEntity<ExceptionWrapper> handleGenericException(Throwable exception){
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionWrapper("Action failed: An error ocurred!",HttpStatus.INTERNAL_SERVER_ERROR));
    }
    @ExceptionHandler({AddressNotFoundException.class,CustomerNotFoundException.class,DiscountNotFoundException.class,
                       OrderNotFoundException.class,ProductNotFoundException.class})
    public ResponseEntity<ExceptionWrapper> handleNotFoundException(Throwable exception){

        exception.printStackTrace();
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionWrapper);

    }
    @ExceptionHandler(CurrencyInvalidException.class)
    public ResponseEntity<ExceptionWrapper> handleCurrencyInvalidException(CurrencyInvalidException exception){
        exception.printStackTrace();
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionWrapper);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionWrapper> handleValidationExceptions(MethodArgumentNotValidException exception){

        exception.printStackTrace();

        ExceptionWrapper exceptionWrapper = new ExceptionWrapper("Invalid input(s)",HttpStatus.BAD_REQUEST);
        List<ValidationException> validationExceptions = new ArrayList<>();

        for (ObjectError error : exception.getBindingResult().getAllErrors()){

            String fieldName = ((FieldError) error).getField();
            Object rejectedValue = ((FieldError) error).getRejectedValue();
            String errorMessage = error.getDefaultMessage();

            ValidationException validationException = new ValidationException(fieldName, rejectedValue, errorMessage);
            validationExceptions.add(validationException);

        }
        exceptionWrapper.setValidationExceptions(validationExceptions);
        exceptionWrapper.setErrorCount(validationExceptions.size());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionWrapper);

    }
}
