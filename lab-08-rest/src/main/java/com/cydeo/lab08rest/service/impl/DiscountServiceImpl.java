package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.DiscountDTO;
import com.cydeo.lab08rest.entity.Discount;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.DiscountRepository;
import com.cydeo.lab08rest.service.DiscountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final MapperUtil mapperUtil;

    public DiscountServiceImpl(DiscountRepository discountRepository, MapperUtil mapperUtil) {
        this.discountRepository = discountRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<DiscountDTO> getAllDiscount() {
        return discountRepository.findAll().stream().map(discount-> mapperUtil.convert(discount,new DiscountDTO())).collect(Collectors.toList());
    }

    @Override
    public DiscountDTO getDiscountByName(String name) {
        Discount discount = discountRepository.findFirstByName(name);
        return mapperUtil.convert(discount, new DiscountDTO());
    }

    @Override
    public DiscountDTO createDiscount(DiscountDTO discountDTO) {
        Discount discount = mapperUtil.convert(discountDTO, new Discount());
        return mapperUtil.convert(discountRepository.save(discount), new DiscountDTO());
    }

    @Override
    public DiscountDTO updateDiscount(DiscountDTO discountDTO) {
        Discount discount = mapperUtil.convert(discountDTO, new Discount());
        Discount discountToSave = discountRepository.save(discount);
        DiscountDTO updatedDiscount = mapperUtil.convert(discountToSave, new DiscountDTO());
        updatedDiscount.setId(updatedDiscount.getId());
        updatedDiscount.setName(updatedDiscount.getName());
        updatedDiscount.setDiscount(updatedDiscount.getDiscount());
        updatedDiscount.setDiscountType(updatedDiscount.getDiscountType());

        return updatedDiscount;
    }
}
