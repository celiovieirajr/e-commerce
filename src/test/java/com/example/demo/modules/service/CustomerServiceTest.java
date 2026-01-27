package com.example.demo.modules.service;

import com.example.demo.modules.dto.CustomerRequestDto;
import com.example.demo.modules.dto.CustomerResponseDto;
import com.example.demo.modules.mapper.CustomerMapper;
import com.example.demo.modules.domain.Customer;
import com.example.demo.modules.repository.CustomerRepository;
import com.example.demo.modules.domain.Viacep;
import com.example.demo.utils.CpfUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock // objetos "falsos"
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private CpfUtils cpfUtils;

    @InjectMocks
    private CustomerImplementsService customerService;

    private CustomerRequestDto createCustomerRequest() {
        CustomerRequestDto requestDto = new CustomerRequestDto();
        requestDto.setName("Célio");
        requestDto.setCpf("91421137097");

        Viacep viacep = new Viacep();
        viacep.setCep("13871096");
        viacep.setLogradouro("Rua Três");
        viacep.setComplemento("Apto 101");
        viacep.setUnidade("UN1");
        viacep.setBairro("Jardim Santa Rita");
        viacep.setLocalidade("São João da Boa Vista");
        viacep.setUf("SP");
        viacep.setEstado("São Paulo");
        viacep.setRegiao("Sudeste");
        viacep.setIbge("3550308");

        requestDto.setAddres(viacep);
        return requestDto;
    }

    private Customer createCustomer(CustomerRequestDto requestDto) {
        Customer model = new Customer();
        model.setId(1L);
        model.setName(requestDto.getName());
        model.setCpf(requestDto.getCpf());
        model.setAddres(requestDto.getAddres());


        return model;
    }

    private CustomerResponseDto createCustomerResponse(Customer customer) {
        CustomerResponseDto responseDto = new CustomerResponseDto();
        responseDto.setCustomerId(customer.getId());
        responseDto.setName(customer.getName());
        responseDto.setCpf(customer.getCpf());
        responseDto.setAddres(customer.getAddres());

        return responseDto;
    }

    @Test
    void insertCustomer_success() {

        CustomerRequestDto requestDto = createCustomerRequest();
        Customer model = createCustomer(requestDto);
        CustomerResponseDto responseDto = createCustomerResponse(model);


        Customer customerTest = new Customer();
        customerTest.setName(requestDto.getName());
        customerTest.setCpf(requestDto.getCpf());
        customerTest.setAddres(requestDto.getAddres());



        when(customerMapper.toModel(requestDto)).thenReturn(model);
        when(cpfUtils.isValidCPF(nullable(String.class))).thenReturn(true);
        when(customerRepository.save(any(Customer.class))).thenReturn(model);
        when(customerMapper.toResponse(model)).thenReturn(responseDto);

        CustomerResponseDto result = customerService.insertCustomer(requestDto);


        assertEquals(responseDto.getName(), result.getName());
        assertEquals(responseDto.getCpf(), result.getCpf());
        assertEquals(responseDto.getAddres(), result.getAddres());
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void findByIdCustomer_success() {
        CustomerRequestDto requestDto = createCustomerRequest();
        Customer model = createCustomer(requestDto);
        CustomerResponseDto response = createCustomerResponse(model);

        when(customerRepository.findById(model.getId())).thenReturn(Optional.of(model));
        when(customerMapper.toResponse(model)).thenReturn(response);

        CustomerResponseDto result = customerService.findCustomerById(model.getId());

        assertEquals(response.getCustomerId(), result.getCustomerId());
        assertEquals(response.getName(), result.getName());
        assertEquals(response.getCpf(), result.getCpf());
        verify(customerRepository).findById(model.getId());
    }

    @Test
    void findAllCustomer_success() {
        CustomerRequestDto requestDto = createCustomerRequest();
        Customer model = createCustomer(requestDto);
        CustomerResponseDto response = createCustomerResponse(model);

        when(customerRepository.findAll()).thenReturn(List.of(model));
        when(customerMapper.toResponse(model)).thenReturn(response);

        List<CustomerResponseDto> result = customerService.findAllCustomer();

        assertEquals(1, result.size());
        assertEquals(response.getCustomerId(), result.get(0).getCustomerId());
        assertEquals(response.getName(), result.get(0).getName());
        assertEquals(response.getCpf(), result.get(0).getCpf());
        assertEquals(response.getAddres(), result.get(0).getAddres());

        verify(customerRepository).findAll();
        verify(customerMapper).toResponse(model);
    }
}
