package com.example.demo.modules.itensSales.service;

import com.example.demo.modules.itensSales.dto.ItemSaleRequestDto;
import com.example.demo.modules.itensSales.dto.ItemSaleResponseDto;

import java.util.List;

public interface IItemSaleService {

    ItemSaleResponseDto insertItemSaleService(ItemSaleRequestDto itemSaleRequestDto, Long idProduct);
    ItemSaleResponseDto findItemSaleById(Long id);
    List<ItemSaleResponseDto> findAllItemSale();
    ItemSaleResponseDto updateItemSaleById(Long id, ItemSaleRequestDto itemSaleRequestDto);
    void deleteItemSaleById(Long id);
}
