package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.CustomerDTO;
import com.cydeo.lab08rest.entity.Customer;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.CustomerRepository;
import com.cydeo.lab08rest.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final MapperUtil mapperUtil;

    public CustomerServiceImpl(CustomerRepository customerRepository, MapperUtil mapperUtil) {
        this.customerRepository = customerRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(customer-> mapperUtil.convert(customer, new CustomerDTO())).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        Customer customer= customerRepository.retrieveByCustomerEmail(email);
        return mapperUtil.convert(customer, new CustomerDTO());
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = mapperUtil.convert(customerDTO, new Customer());
        return mapperUtil.convert(customerRepository.save(customer), new CustomerDTO());
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer = mapperUtil.convert(customerDTO, new Customer());
        Customer customerToSave = customerRepository.save(customer);
        CustomerDTO updateCustomer = mapperUtil.convert(customerToSave, new CustomerDTO());
        updateCustomer.setId(updateCustomer.getId());
        updateCustomer.setFirstName(updateCustomer.getFirstName());
        updateCustomer.setLastName(updateCustomer.getLastName());
        updateCustomer.setUserName(updateCustomer.getUserName());
        updateCustomer.setEmail(updateCustomer.getEmail());
        return updateCustomer;
    }
}
