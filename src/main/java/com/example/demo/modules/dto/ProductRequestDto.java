package com.example.demo.modules.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {

    @NotBlank(message = "codProduct is required")
    @NotNull(message = "codProduct cannot be null")
    @Size(min = 4, max = 10, message = "codProduct must be between 4 and 10 characters.")
    private String codProduct;

    @NotBlank(message = "description is required")
    @NotNull(message = "description cannot be null")
    @Size(min = 4, max = 10, message = "description must be between 4 and 10 characters.")
    private String description;

    @PositiveOrZero(message = "the price is less than zero")
    @NotNull(message = "price cannot be null")
    private BigDecimal price;
}
