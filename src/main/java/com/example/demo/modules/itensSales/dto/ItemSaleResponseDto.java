package com.example.demo.modules.itensSales.dto;

import com.example.demo.modules.products.dto.ProductResponseDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemSaleResponseDto {

    private long id;
    private int quantity;
    private BigDecimal amount;
    private BigDecimal totalAmount;
    private ProductResponseDto productResponseDto;

}
