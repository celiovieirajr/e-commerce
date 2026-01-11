package com.example.demo.modules.itensSales.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemSaleRequestDto {

    private Long productId;
    private Integer quantity;
    private BigDecimal amount;
    private BigDecimal totalAmount;
}

