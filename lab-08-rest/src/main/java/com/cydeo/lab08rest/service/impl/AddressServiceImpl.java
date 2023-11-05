package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.AddressDTO;
import com.cydeo.lab08rest.entity.Address;
import com.cydeo.lab08rest.entity.Customer;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.AddressRepository;
import com.cydeo.lab08rest.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;

    public AddressServiceImpl(AddressRepository addressRepository, MapperUtil mapperUtil) {
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll().stream().map(address-> mapperUtil.convert(address , new AddressDTO())).collect(Collectors.toList());
    }

    @Override
    public List<AddressDTO> getAllAddressStartsWith(String address) {
        return addressRepository.findAllByStreetStartingWith(address).stream().map(address1-> mapperUtil.convert(address1, new AddressDTO())).collect(Collectors.toList());
    }

    @Override
    public List<AddressDTO> getAllAddressByCustomerId(Long customerId) {
        return addressRepository.retrieveByCustomerId(customerId).stream()
                .map(address->mapperUtil.convert(address,new AddressDTO())).collect(Collectors.toList());
    }

    @Override
    public List<AddressDTO> getAllByCustomerAndName(Customer customer, String name) {
        return addressRepository.findAllByCustomerAndName(customer,name).stream()
                .map(address->mapperUtil.convert(address,new AddressDTO())).collect(Collectors.toList());
    }

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = mapperUtil.convert(addressDTO, new Address());
        return mapperUtil.convert(addressRepository.save(address), new AddressDTO());
    }

    @Override
    public AddressDTO updateAddress(AddressDTO addressDTO) {
        Address address = mapperUtil.convert(addressDTO, new Address());
        Address addressToSAve = addressRepository.save(address);
        AddressDTO updatedAddress= mapperUtil.convert(addressToSAve, new AddressDTO());
        updatedAddress.setId(updatedAddress.getId());
        updatedAddress.setName(updatedAddress.getName());
        updatedAddress.setZipCode(updatedAddress.getZipCode());
        updatedAddress.setStreet(updatedAddress.getStreet());
        updatedAddress.setCustomerId(updatedAddress.getCustomerId());
        return updatedAddress;
    }
}
