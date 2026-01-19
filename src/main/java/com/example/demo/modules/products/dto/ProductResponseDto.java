package com.example.demo.modules.products.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonPropertyOrder({
        "id",
        "codProduct",
        "description",
        "price"
})
public class ProductResponseDto {

    private Long id;
    private String codProduct;
    private String description;
    private BigDecimal price;
}
