package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.DiscountDTO;

import java.util.List;

public interface DiscountService {
    List<DiscountDTO> getAllDiscount();
    DiscountDTO getDiscountByName(String name);
    DiscountDTO createDiscount(DiscountDTO discountDTO);
    DiscountDTO updateDiscount(DiscountDTO discountDTO);
}
