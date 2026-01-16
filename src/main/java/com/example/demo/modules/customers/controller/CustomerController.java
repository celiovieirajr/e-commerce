package com.example.demo.modules.customers.controller;

import com.example.demo.modules.customers.dto.CustomerRequestDto;
import com.example.demo.modules.customers.dto.CustomerResponseDto;
import com.example.demo.modules.customers.service.CustomerService;
import com.example.demo.modules.customers.service.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/customer")
public class CustomerController {

    public ICustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> insertCustomerController(@RequestBody CustomerRequestDto requestDto) {
        CustomerResponseDto model = customerService.insertCustomer(requestDto);
        return ResponseEntity.ok().body(model);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updatedCustomerController(@PathVariable Long id,
                                                                         @RequestBody CustomerRequestDto requestDto) {
        CustomerResponseDto responseDto = customerService.updatedCustomer(id, requestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> findCustomerByIdController(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> findAllCustomerController() {
        return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerByIdController(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}
