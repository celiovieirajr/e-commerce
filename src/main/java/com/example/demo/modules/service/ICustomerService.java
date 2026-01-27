package com.example.demo.modules.service;

import com.example.demo.modules.dto.CustomerRequestDto;
import com.example.demo.modules.dto.CustomerResponseDto;

import java.util.List;

public interface ICustomerService {

    CustomerResponseDto insertCustomer(CustomerRequestDto requestDto);
    CustomerResponseDto updatedCustomer(Long id, CustomerRequestDto requestDto);
    CustomerResponseDto findCustomerById(Long id);
    List<CustomerResponseDto> findAllCustomer();
    void deleteCustomerById(Long id);
}
