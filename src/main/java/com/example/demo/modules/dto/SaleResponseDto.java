package com.example.demo.modules.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonPropertyOrder({
        "saleId",
        "customerId",
        "customerName",
        "document",
        "itens",
        "totalAmount"
})
public class SaleResponseDto {

    private Long saleId;
    private Long customerId;
    private String customerName;
    private String document;
    private BigDecimal totalAmount;
    private List<ItemSaleResponseDto> itens;
}
