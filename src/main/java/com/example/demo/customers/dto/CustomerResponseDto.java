package com.example.demo.customers.dto;

import com.example.demo.viaceps.model.Viacep;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class CustomerResponseDto {

    private long id;
    private String name;

    @Embedded
    private Viacep addres;
}
