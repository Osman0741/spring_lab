package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.enums.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDTO> getAllOrders();
    List<OrderDTO> getAllOrdersByPaymentMethod(PaymentMethod paymentMethod);

    List<OrderDTO> getAllOrdersByEmail(String email);
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(OrderDTO orderDTO);


    OrderDTO getOrderByIdAndOptionalCurrency(Long orderId, Optional<String> currency);
}
