package com.example.demo.modules.customers.mapper;

import com.example.demo.modules.customers.dto.CustomerRequestDto;
import com.example.demo.modules.customers.dto.CustomerResponseDto;
import com.example.demo.modules.customers.model.Customer;
import com.example.demo.modules.viaceps.model.Viacep;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toModel(CustomerRequestDto requestDto) {
        Customer model = new Customer();
        model.setName(requestDto.getName());
        model.setCpf(requestDto.getCpf());

        if (requestDto.getAddres() != null) {
            Viacep viacep = new Viacep();
            viacep.setCep(requestDto.getAddres().getCep());
            viacep.setLogradouro(requestDto.getAddres().getLogradouro());
            viacep.setComplemento(requestDto.getAddres().getComplemento());
            viacep.setUnidade(requestDto.getAddres().getUnidade());
            viacep.setBairro(requestDto.getAddres().getBairro());
            viacep.setLocalidade(requestDto.getAddres().getLocalidade());
            viacep.setUf(requestDto.getAddres().getUf());
            viacep.setEstado(requestDto.getAddres().getEstado());
            viacep.setRegiao(requestDto.getAddres().getRegiao());
            viacep.setIbge(requestDto.getAddres().getIbge());

            model.setAddres(viacep);
        }

        return model;
    }

    public CustomerResponseDto toResponse(Customer model) {
        CustomerResponseDto responseDto = new CustomerResponseDto();
        responseDto.setId(model.getId());
        responseDto.setName(model.getName());
        responseDto.setCpf(model.getCpf());

        if (model.getAddres() != null) {
            responseDto.setAddres(model.getAddres());
        }

        return responseDto;
    }
}
