package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.CustomerDTO;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllCustomers(){
        return ResponseEntity.ok(new ResponseWrapper("Customers are successfully retrieved", customerService.getAllCustomers(), HttpStatus.ACCEPTED ));
    }
    @GetMapping("/{email}")
    public ResponseEntity<ResponseWrapper> getAllCustomerByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(new ResponseWrapper("Customer is successfully retrieved", customerService.getCustomerByEmail(email), HttpStatus.OK ));
    }
    @PostMapping()
    public ResponseEntity<ResponseWrapper> createCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(new ResponseWrapper("Customer is created", customerService.createCustomer(customerDTO), HttpStatus.CREATED ));
    }
    @PutMapping()
    public ResponseEntity<ResponseWrapper> updateCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(new ResponseWrapper("Customer is updated", customerService.updateCustomer(customerDTO), HttpStatus.CREATED ));
    }
}
