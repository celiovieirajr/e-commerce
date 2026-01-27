package com.example.demo.modules.service;

import com.example.demo.modules.dto.SaleRequestDto;
import com.example.demo.modules.dto.SaleResponseDto;

import java.util.List;

public interface ISaleService {
    SaleResponseDto insertSale(SaleRequestDto saleRequestDto);
    SaleResponseDto findSaleById(Long id);
    List<SaleResponseDto> findAllSale();
    SaleResponseDto updatedSale(Long id, SaleRequestDto dto);
    void deleteSaleById(Long id);
}
