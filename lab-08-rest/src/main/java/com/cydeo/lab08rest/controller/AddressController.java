package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.AddressDTO;
import com.cydeo.lab08rest.entity.Customer;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllAddresses() {
        return ResponseEntity.ok(new ResponseWrapper("Address are successfully retrieved", addressService.getAllAddresses(), HttpStatus.ACCEPTED));
    }
    @GetMapping("startsWith/{address}")
    public ResponseEntity<ResponseWrapper> getAllAddressStartsWith(@PathVariable("address") String address) {
        return ResponseEntity.ok(new ResponseWrapper("Address is successfully retrieved", addressService.getAllAddressStartsWith(address), HttpStatus.OK));
    }
    @GetMapping("customer/{id}")
    public ResponseEntity<ResponseWrapper> getAllAddressByCustomerId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new ResponseWrapper("Address is successfully retrieved", addressService.getAllAddressByCustomerId(id), HttpStatus.OK));
    }
    @GetMapping("customer/{id}/name/{name}")
    public ResponseEntity<ResponseWrapper> getAllAddressByCustomerAndName(@PathVariable("id") Customer customer, @PathVariable("name") String name) {
        return ResponseEntity.ok(new ResponseWrapper("Address is successfully retrieved", addressService.getAllByCustomerAndName(customer,name), HttpStatus.OK));
    }
    @PostMapping
    public ResponseEntity<ResponseWrapper> createAddress(@RequestBody AddressDTO addressDTO)  {
        return ResponseEntity.ok(new ResponseWrapper("Address is created", addressService.createAddress(addressDTO), HttpStatus.CREATED));
    }
    @PutMapping
    public ResponseEntity<ResponseWrapper> updateAddress(@RequestBody AddressDTO addressDTO)  {
        return ResponseEntity.ok(new ResponseWrapper("Address is updated", addressService.updateAddress(addressDTO), HttpStatus.CREATED));
    }
}
