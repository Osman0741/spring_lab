package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.client.CurrencyClient;
import com.cydeo.lab08rest.dto.CurrencyDTO;
import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.entity.Order;
import com.cydeo.lab08rest.enums.Currency;
import com.cydeo.lab08rest.enums.PaymentMethod;
import com.cydeo.lab08rest.exception.CurrencyInvalidException;
import com.cydeo.lab08rest.exception.OrderNotFoundException;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.OrderRepository;
import com.cydeo.lab08rest.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MapperUtil mapperUtil;
    private final CurrencyClient currencyClient;

    @Value("e1aa8373b11019fbf69f9afe5cf57f66")
    private String access_key;

    public OrderServiceImpl(OrderRepository orderRepository, MapperUtil mapperUtil, CurrencyClient currencyClient) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
        this.currencyClient = currencyClient;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> mapperUtil.convert(order, new OrderDTO())).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrdersByPaymentMethod(PaymentMethod paymentMethod) {
        return orderRepository.findAllByPayment_PaymentMethod(paymentMethod).stream()
                .map(order -> mapperUtil.convert(order, new OrderDTO())).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrdersByEmail(String email) {
        return orderRepository.findAllByCustomer_Email(email).stream()
                .map(order -> mapperUtil.convert(order, new OrderDTO())).collect(Collectors.toList());
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = mapperUtil.convert(orderDTO, new Order());

        return mapperUtil.convert(orderRepository.save(order), new OrderDTO());
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Order order = mapperUtil.convert(orderDTO, new Order());
        Order orderToSave = orderRepository.save(order);
        OrderDTO updatedOrder = mapperUtil.convert(orderToSave, new OrderDTO());
        updatedOrder.setCartId(updatedOrder.getCartId());
        updatedOrder.setPaidPrice(updatedOrder.getPaidPrice());
        updatedOrder.setTotalPrice(updatedOrder.getTotalPrice());
        updatedOrder.setCustomerId(updatedOrder.getCustomerId());
        updatedOrder.setPaymentId(updatedOrder.getPaymentId());
        updatedOrder.setId(updatedOrder.getId());


        return updatedOrder;
    }

    @Override
    public OrderDTO getOrderByIdAndOptionalCurrency(Long orderId, Optional<String> currency) {

        Order foundOrder = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("No Order Found!"));

        OrderDTO orderToReturn = mapperUtil.convert(foundOrder,new OrderDTO());

        BigDecimal currencyRate = getCurrencyRate(currency);

        orderToReturn.setPaidPrice(convertCurrency(foundOrder.getPaidPrice(),currencyRate));
        orderToReturn.setTotalPrice(convertCurrency(foundOrder.getTotalPrice(),currencyRate));

        return orderToReturn;
    }

    private BigDecimal convertCurrency(BigDecimal dollarAmount, BigDecimal currencyRate) {
        return dollarAmount.multiply(currencyRate).setScale(2, RoundingMode.CEILING);
    }

    private BigDecimal getCurrencyRate(Optional<String> currency) {

        if(currency.isPresent() && !currency.get().toUpperCase().equals("USD")){
            validateCurrency(currency.get());
//            BigDecimal rate = currencyClient.getCurrency(access_key, currency).getQuotes().get(currency);
            CurrencyDTO responseBody = currencyClient.getCurrency(access_key,currency.get()).getBody();
            BigDecimal rate = responseBody.getQuotes().get("USD"+currency.get().toUpperCase());
            return rate;
        }
        return BigDecimal.ONE;

    }
    private void validateCurrency(String currency){

    List<String> validateCurrency =   Stream.of(Currency.values())
               .map(eachCurrency-> eachCurrency.value)
               .collect(Collectors.toList());

       boolean isValidCurrency = validateCurrency.contains(currency);

       if(!isValidCurrency){
           throw new CurrencyInvalidException("Invalid Currency");
       }
    }


}
