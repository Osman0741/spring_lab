package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.AddressDTO;
import com.cydeo.lab08rest.entity.Customer;

import java.util.List;

public interface AddressService {

    List<AddressDTO> getAllAddresses();
    List<AddressDTO> getAllAddressStartsWith(String address);

    List<AddressDTO> getAllAddressByCustomerId(Long customerId);
    List<AddressDTO> getAllByCustomerAndName(Customer customer, String name);
    AddressDTO createAddress(AddressDTO addressDTO);
    AddressDTO updateAddress(AddressDTO addressDTO);
}
