package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.client.CurrencyClient;
import com.cydeo.lab08rest.dto.CurrencyDTO;
import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.entity.Order;
import com.cydeo.lab08rest.enums.PaymentMethod;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.OrderRepository;
import com.cydeo.lab08rest.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public OrderDTO findById(Long id) throws Exception {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new Exception("No Order Found"));
        OrderDTO orderDTO = mapperUtil.convert(order,new OrderDTO());

        return orderDTO;

    }

    @Override
    public OrderDTO getCurrency(Long id, String currency) throws Exception {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new Exception("No Order Found"));
        OrderDTO orderDTO = mapperUtil.convert(order,new OrderDTO());
        orderDTO.setPaidPrice(retrieveCurrency(currency).getQuotes().getUsdtry().multiply(orderDTO.getPaidPrice()));
        orderDTO.setTotalPrice(retrieveCurrency(currency).getQuotes().getUsdtry().multiply(orderDTO.getTotalPrice()));
        return orderDTO;
    }

    private CurrencyDTO retrieveCurrency(String currency) {
        return currencyClient.getCurrency(access_key,currency,"USD",1);
    }


}
