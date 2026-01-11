package com.example.demo.modules.sales.dto;

import com.example.demo.modules.itensSales.dto.ItemSaleRequestDto;
import com.example.demo.modules.itensSales.dto.ItemSaleResponseDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleResponseDto {

    private Long id;
    private Long idCustomer;
    private BigDecimal totalAmount;
    private List<ItemSaleResponseDto> itens;
}
