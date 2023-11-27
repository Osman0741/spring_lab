package com.cydeo.lab08rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationException {

    private String errorField;
    private Object rejectedValue;
    private String reason;
}
