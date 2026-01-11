package com.example.demo.modules.sales.dto;


import com.example.demo.modules.itensSales.dto.ItemSaleRequestDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data

public class SaleRequestDto {
    private Long idCustomer;
    private BigDecimal totalAmount;
    private List<ItemSaleRequestDto> itens;
}

