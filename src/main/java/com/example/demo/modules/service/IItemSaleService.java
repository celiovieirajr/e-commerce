package com.example.demo.modules.service;

import com.example.demo.modules.dto.ItemSaleRequestDto;
import com.example.demo.modules.dto.ItemSaleResponseDto;

import java.util.List;

public interface IItemSaleService {
    ItemSaleResponseDto insertItemSaleService(ItemSaleRequestDto itemSaleRequestDto, Long idSale);
    ItemSaleResponseDto findItemSaleById(Long idSale, Long idItemSale);
    List<ItemSaleResponseDto> findAllItemSale(Long saleId);
    ItemSaleResponseDto updateItemSaleById(Long saleId, Long itemSaleId, ItemSaleRequestDto itemSaleRequestDto);
    void deleteItemSaleById(Long saleId, Long itemSaleId);



}
