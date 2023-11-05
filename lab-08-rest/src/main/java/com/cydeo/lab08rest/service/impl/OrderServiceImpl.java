package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.entity.Order;
import com.cydeo.lab08rest.enums.PaymentMethod;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.OrderRepository;
import com.cydeo.lab08rest.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MapperUtil mapperUtil;

    public OrderServiceImpl(OrderRepository orderRepository, MapperUtil mapperUtil) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order-> mapperUtil.convert(order, new OrderDTO())).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrdersByPaymentMethod(PaymentMethod paymentMethod) {
        return orderRepository.findAllByPayment_PaymentMethod(paymentMethod).stream()
                .map(order->mapperUtil.convert(order,new OrderDTO())).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrdersByEmail(String email) {
        return orderRepository.findAllByCustomer_Email(email).stream()
                .map(order->mapperUtil.convert(order,new OrderDTO())).collect(Collectors.toList());
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = mapperUtil.convert(orderDTO ,new Order());

        return mapperUtil.convert(orderRepository.save(order), new OrderDTO());
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Order order = mapperUtil.convert(orderDTO ,new Order());
        Order orderToSave= orderRepository.save(order);
        OrderDTO updatedOrder = mapperUtil.convert(orderToSave,new OrderDTO());
        updatedOrder.setCartId(updatedOrder.getCartId());
        updatedOrder.setPaidPrice(updatedOrder.getPaidPrice());
        updatedOrder.setTotalPrice(updatedOrder.getTotalPrice());
        updatedOrder.setCustomerId(updatedOrder.getCustomerId());
        updatedOrder.setPaymentId(updatedOrder.getPaymentId());
        updatedOrder.setId(updatedOrder.getId());


        return updatedOrder;
    }
}
