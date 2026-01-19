package com.example.demo.modules.sales.dto;


import com.example.demo.modules.itensSales.dto.ItemSaleRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleRequestDto {

    @NotNull(message = "idCustomer cannot be null")
    @NotBlank(message = "idCustomer cannot be blank")
    private Long idCustomer;

    @NotNull(message = "itens cannot be null")
    @NotEmpty(message = "itens cannot be empy")
    private List<ItemSaleRequestDto> itens;
}

