package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.enums.PaymentMethod;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;



    public OrderController(OrderService orderService, RestTemplate restTemplate) {
        this.orderService = orderService;


    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllOrders() {
        return ResponseEntity.ok(new ResponseWrapper("Orders are successfully retrieved", orderService.getAllOrders(), HttpStatus.ACCEPTED));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWrapper> getOrderById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity
                .ok(new ResponseWrapper("Order is successfully retrieved",
                        orderService.findById(id), HttpStatus.OK));
    }
    @GetMapping("/currency/{id}")
    public ResponseEntity<ResponseWrapper> getCurrentById(@PathVariable("id") Long id, @RequestParam String currency ) throws Exception {

        return ResponseEntity
                .ok(new ResponseWrapper("Order is successfully retrieved",
                        orderService.getCurrency(id,currency), HttpStatus.OK));
    }


    @GetMapping("/paymentMethod/{paymentMethod}")
    public ResponseEntity<ResponseWrapper> getAllOrdersByPaymentMethod(@PathVariable("paymentMethod") PaymentMethod paymentMethod) {
        return ResponseEntity
                .ok(new ResponseWrapper("Orders is successfully retrieved",
                        orderService.getAllOrdersByPaymentMethod(paymentMethod), HttpStatus.OK));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseWrapper> getAllOrdersByEmail(@PathVariable("email") String email) {
        return ResponseEntity
                .ok(new ResponseWrapper("Orders is successfully retrieved",
                        orderService.getAllOrdersByEmail(email), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(new ResponseWrapper("Order is created", orderService.createOrder(orderDTO), HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(new ResponseWrapper("Order is updated", orderService.updateOrder(orderDTO), HttpStatus.CREATED));
    }
}
