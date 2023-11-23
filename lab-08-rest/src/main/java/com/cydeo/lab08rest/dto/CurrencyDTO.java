package com.cydeo.lab08rest.dto;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CurrencyDTO {

    private Map<String, BigDecimal> quotes;

}