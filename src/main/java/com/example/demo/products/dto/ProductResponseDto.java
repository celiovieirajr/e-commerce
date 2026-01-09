package com.example.demo.products.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDto {

    private Long id;
    private String description;
    private BigDecimal price;
}
