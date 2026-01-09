package com.example.demo.modules.products.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {

    private String description;
    private BigDecimal price;
}
