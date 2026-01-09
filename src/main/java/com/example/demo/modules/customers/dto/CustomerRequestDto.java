package com.example.demo.modules.customers.dto;

import com.example.demo.modules.viaceps.model.Viacep;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class CustomerRequestDto {

    private String name;

    @Embedded
    private Viacep addres;
}
