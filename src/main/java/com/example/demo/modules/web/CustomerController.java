package com.example.demo.modules.web;

import com.example.demo.modules.dto.CustomerRequestDto;
import com.example.demo.modules.dto.CustomerResponseDto;
import com.example.demo.modules.service.CustomerImplementsService;
import com.example.demo.modules.service.ICustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("api/v1/customers/")
@Tag(name = "Customer", description = "Endpoints for manage Customer")
public class CustomerController {

    public ICustomerService customerService;

    public CustomerController(CustomerImplementsService customerImplementsService) {
        this.customerService = customerImplementsService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> insertCustomerController(@Valid @RequestBody CustomerRequestDto requestDto) {
        CustomerResponseDto model = customerService.insertCustomer(requestDto);
        return ResponseEntity.ok().body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updatedCustomerController(@Valid @PathVariable Long id,
                                                                         @RequestBody CustomerRequestDto requestDto) {
        CustomerResponseDto responseDto = customerService.updatedCustomer(id, requestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> findCustomerByIdController(@Valid @PathVariable Long id) {
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
