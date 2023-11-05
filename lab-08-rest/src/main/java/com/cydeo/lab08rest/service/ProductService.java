package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.ProductDTO;


import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO getProductByName(String name);

    List<ProductDTO> getTop3Product();

    Integer countProductThanPrice(BigDecimal price);

    List<ProductDTO> retrieveProductListGreaterThanPriceAndLowerThanRemainingQuantity(BigDecimal price, Integer quantity);

    List<ProductDTO> retrieveProductListByCategory(Long categoryId);

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> retrieveProductListByCategory(List<Long> categoryId, BigDecimal price);

    ProductDTO updateProduct( ProductDTO productDTO);

}
