package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.DiscountDTO;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllDiscount(){
        return ResponseEntity.ok(new ResponseWrapper("Discounts are successfully retrieved", discountService.getAllDiscount(), HttpStatus.ACCEPTED ));
    }
    @GetMapping("/{name}")
    public ResponseEntity<ResponseWrapper> getDiscountByName(@PathVariable("name") String name){
        return ResponseEntity.ok(new ResponseWrapper("Discount are successfully retrieved", discountService.getDiscountByName(name), HttpStatus.OK ));
    }
    @PostMapping
    public ResponseEntity<ResponseWrapper> createDiscount(@RequestBody DiscountDTO discountDTO){
        return ResponseEntity.ok(new ResponseWrapper("Discount is created", discountService.createDiscount(discountDTO),HttpStatus.CREATED));
    }
    @PutMapping
    public ResponseEntity<ResponseWrapper> updateDiscount(@RequestBody DiscountDTO discountDTO){
        return ResponseEntity.ok(new ResponseWrapper("Discount is updated", discountService.updateDiscount(discountDTO),HttpStatus.CREATED));
    }
}
