package com.example.demo.modules.itensSales.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class ItemSaleRequestDto {

    @NotNull(message = "product cannot be null")
    @Positive(message = "product cannot be negative")
    private Long productId;

    @NotNull(message = "quantity cannot be null")
    @Positive(message = "quantity cannote be negative")
    private Integer quantity;
}

