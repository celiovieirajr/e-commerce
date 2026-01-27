package com.example.demo.modules.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonPropertyOrder({
        "itemSaleId",
        "quantity",
        "amount",
        "codProduct",
        "description",
        "totalAmout"
})
public class ItemSaleResponseDto {

    private long itemSaleId;
    private int quantity;
    private BigDecimal amount;
    private String codProduct;
    private String description;
    private BigDecimal totalAmount;
}
