package com.example.demo.modules.dto;

import com.example.demo.modules.domain.Viacep;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "cpf", "addres"})
public class CustomerResponseDto {

    private long customerId;
    private String name;
    private String cpf;

    @Embedded
    private Viacep addres;
}
