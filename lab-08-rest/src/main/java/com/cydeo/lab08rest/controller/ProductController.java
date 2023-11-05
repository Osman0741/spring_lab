package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.ProductDTO;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllProducts(){
        return ResponseEntity.ok(new ResponseWrapper("Products are successfully retrieved", productService.getAllProducts(),HttpStatus.ACCEPTED));
    }
    @GetMapping("/{name}")
    public ResponseEntity<ResponseWrapper> getProductByName(@PathVariable("name") String name){
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully retrieved", productService.getProductByName(name),HttpStatus.OK));
    }
    @GetMapping("/top3")
    public ResponseEntity<ResponseWrapper> getTop3Product(){
        return ResponseEntity.ok(new ResponseWrapper("Products are successfully retrieved",productService.getTop3Product(),HttpStatus.ACCEPTED));
    }
    @GetMapping("/price/{price}")
    public ResponseEntity<ResponseWrapper> countProductThanPrice(@PathVariable("price")BigDecimal price){
        return ResponseEntity.ok(new ResponseWrapper("Products are successfully retrieved",productService.countProductThanPrice(price),HttpStatus.OK));
    }
    @GetMapping("/price/{price}/quantity/{quantity}")
    public ResponseEntity<ResponseWrapper> getProductBasedOnPriceAndQuantity(@PathVariable("price") BigDecimal price,@PathVariable("quantity") Integer quantity){
        return ResponseEntity.ok(new ResponseWrapper("Products are successfully retrieved", productService.retrieveProductListGreaterThanPriceAndLowerThanRemainingQuantity(price,quantity),HttpStatus.OK));
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<ResponseWrapper> getProductListByCategory(@PathVariable("id") Long categoryId){
        return ResponseEntity.ok(new ResponseWrapper("Products are successfully retrieved", productService.retrieveProductListByCategory(categoryId),HttpStatus.OK));
    }
    @PostMapping
    public ResponseEntity<ResponseWrapper> createProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(new ResponseWrapper("Product is created", productService.createProduct(productDTO),HttpStatus.CREATED));
    }
    @PostMapping("/categoryandprice")
    public ResponseEntity<ResponseWrapper> retrieveProductListByCategory(@RequestBody List<Long> courseId, @RequestBody BigDecimal price){
        return ResponseEntity.ok(new ResponseWrapper("Products are successfully retrieved",productService.retrieveProductListByCategory(courseId,price),HttpStatus.OK));
    }
    @PutMapping()
    public ResponseEntity<ResponseWrapper> updateProduct(@RequestBody ProductDTO productDTO){

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseWrapper("Product is updated", productService.updateProduct(productDTO),HttpStatus.CREATED));
    }

}
