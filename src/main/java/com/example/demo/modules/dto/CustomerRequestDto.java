package com.example.demo.modules.dto;

import com.example.demo.modules.domain.Viacep;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class CustomerRequestDto {

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;

    @NotBlank(message = "CPF is required.")
    @CPF(message = "CPF invalid.")
    private String cpf;

    @Embedded
    private Viacep addres;
}
