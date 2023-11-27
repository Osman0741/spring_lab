package com.cydeo.lab08rest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    @NotNull(message = "Cart ID is required.")
    private Long cartId;
    @Min(message = "Paid Price cannot be lower than 1", value = 1)
    private BigDecimal paidPrice;
    @Min(1)
    private BigDecimal totalPrice;
    @NotNull(message = "Customer ID is required.")
    private Long customerId;
    @NotNull(message = "Payment ID is required.")
    private Long paymentId;

   
}
