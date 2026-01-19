package com.example.demo.modules.itensSales.dto;

import com.example.demo.modules.products.dto.ProductResponseDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonPropertyOrder({
        "id",
        "quantity",
        "amount",
        "productResponseDto",
        "totalAmout"
})
public class ItemSaleResponseDto {

    private long id;
    private int quantity;
    private BigDecimal amount;
    private BigDecimal totalAmount;
    private ProductResponseDto productResponseDto;

}
