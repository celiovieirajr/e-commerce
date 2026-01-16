package com.example.demo.modules.customers.service;

import com.example.demo.exception.ApiException;
import com.example.demo.modules.customers.dto.CustomerRequestDto;
import com.example.demo.modules.customers.dto.CustomerResponseDto;
import com.example.demo.modules.customers.mapper.CustomerMapper;
import com.example.demo.modules.customers.model.Customer;
import com.example.demo.modules.customers.repository.CustomerRepository;
import com.example.demo.utils.CpfUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService implements ICustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CpfUtils cpfUtils;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, CpfUtils cpfUtils) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.cpfUtils = cpfUtils;
    }

    public CustomerResponseDto insertCustomer(CustomerRequestDto requestDto) {

        String cpf = cpfUtils.onlyDigits(requestDto.getCpf());

        if (!cpfUtils.isValidCPF(cpf)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Cpf is not valid.");
        }

        requestDto.setCpf(cpf);

        Customer model = customerMapper.toModel(requestDto);
        Customer modelSaved = customerRepository.save(model);

        return customerMapper.toResponse(modelSaved);
    }

    public CustomerResponseDto updatedCustomer(Long id, CustomerRequestDto requestDto) {
        Customer model = customerRepository.findById(id).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "Customer nots exists."));

        model.setName(requestDto.getName());

        if (requestDto.getAddres() != null) {
            model.getAddres().setCep(requestDto.getAddres().getCep());
            model.getAddres().setLogradouro(requestDto.getAddres().getLogradouro());
            model.getAddres().setComplemento(requestDto.getAddres().getComplemento());
            model.getAddres().setUnidade(requestDto.getAddres().getUnidade());
            model.getAddres().setBairro(requestDto.getAddres().getBairro());
            model.getAddres().setLocalidade(requestDto.getAddres().getLocalidade());
            model.getAddres().setUf(requestDto.getAddres().getUf());
            model.getAddres().setEstado(requestDto.getAddres().getEstado());
            model.getAddres().setRegiao(requestDto.getAddres().getRegiao());
            model.getAddres().setIbge(requestDto.getAddres().getIbge());

            model.setAddres(requestDto.getAddres());
        }

        String cpf = cpfUtils.onlyDigits(requestDto.getCpf());

        if (!cpfUtils.isValidCPF(cpf)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Cpf is not valid.");
        }

        model.setCpf(cpf);

        Customer modelSaved = customerRepository.save(model);

        return customerMapper.toResponse(modelSaved);
    }

    public CustomerResponseDto findCustomerById(Long id) {
        return customerRepository.findById(id).map(customerMapper::toResponse).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "Customer nots exists."));
    }

    public List<CustomerResponseDto> findAllCustomer() {
        return customerRepository.findAll().stream().map(customerMapper::toResponse).toList();
    }

    public void deleteCustomerById(Long id) {
        Customer model = customerRepository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Customer nots exits"));
        customerRepository.delete(model);
    }
}
