package com.example.demo.modules.customers.service;

import com.example.demo.modules.customers.dto.CustomerRequestDto;
import com.example.demo.modules.customers.dto.CustomerResponseDto;

import java.util.List;

public interface ICustomerService {

    CustomerResponseDto insertCustomer(CustomerRequestDto requestDto);
    CustomerResponseDto updatedCustomer(Long id, CustomerRequestDto requestDto);
    CustomerResponseDto findCustomerById(Long id);
    List<CustomerResponseDto> findAllCustomer();
    void deleteCustomerById(Long id);
}
