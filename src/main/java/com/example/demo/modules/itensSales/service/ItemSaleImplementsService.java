package com.example.demo.modules.itensSales.service;

import com.example.demo.exception.ApiException;
import com.example.demo.modules.itensSales.dto.ItemSaleRequestDto;
import com.example.demo.modules.itensSales.dto.ItemSaleResponseDto;
import com.example.demo.modules.itensSales.mapper.ItemSaleMapper;
import com.example.demo.modules.itensSales.model.ItemSale;
import com.example.demo.modules.itensSales.repository.ItemSaleRepository;
import com.example.demo.modules.products.model.Product;
import com.example.demo.modules.products.repository.ProductRepository;
import com.example.demo.modules.sales.model.Sale;
import com.example.demo.modules.sales.repository.SaleRepository;
import com.example.demo.modules.sales.service.SaleImplementsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemSaleImplementsService {

    private final ProductRepository productRepository;
    private final ItemSaleRepository itemSaleRepository;
    private final ItemSaleMapper itemSaleMapper;
    private final SaleImplementsService saleImplementsService;
    private final SaleRepository saleRepository;

    public ItemSaleImplementsService(ItemSaleRepository itemSaleRepository, ItemSaleMapper itemSaleMapper, ProductRepository productRepository, SaleImplementsService saleImplementsService, SaleRepository saleRepository) {
        this.itemSaleRepository = itemSaleRepository;
        this.itemSaleMapper = itemSaleMapper;
        this.productRepository = productRepository;
        this.saleImplementsService = saleImplementsService;
        this.saleRepository = saleRepository;
    }

    public ItemSaleResponseDto insertItemSaleService(ItemSaleRequestDto itemSaleRequestDto, Long idSale) {

        Sale sale = saleRepository.findById(idSale).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "Sale nots exists"));

        Product product = productRepository.findById(itemSaleRequestDto.getProductId()).orElseThrow(
                        () -> new ApiException(HttpStatus.NOT_FOUND, "Product nots exists"));

        ItemSale itemSale = itemSaleMapper.toModel(itemSaleRequestDto, sale);
        ItemSale itemSaleSaved = itemSaleRepository.save(itemSale);

        if (idSale != null) {
            saleImplementsService.recalculateTotalAmount(sale);
            saleRepository.save(sale);
        }
        return itemSaleMapper.toResponse(itemSaleSaved);
    }


    public ItemSaleResponseDto findItemSaleById(Long idSale, Long idItemSale) {

            ItemSale itemSale = itemSaleRepository.findById(idItemSale).orElseThrow(
                    () -> new ApiException(HttpStatus.NOT_FOUND, "ItemSale nots exists"));

            if (!itemSale.getSale().getId().equals(idSale)) {
                throw new ApiException(HttpStatus.NOT_FOUND, "Sale nots exists");
            }

            return itemSaleMapper.toResponse(itemSale);
    }

    public List<ItemSaleResponseDto> findAllItemSale(Long saleId) {

        if (saleId == null || saleId == 0) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Sale nots exists");
        }

        Sale sale = saleRepository.findById(saleId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "Sale nots exits"));

        if (sale.getId() == saleId) {
            List<ItemSaleResponseDto> list = itemSaleRepository.findAll().stream().map(itemSaleMapper::toResponse).toList();
            return list;
        }
        return List.of();
    }

    public ItemSaleResponseDto updateItemSaleById(Long saleId, Long itemSaleId, ItemSaleRequestDto itemSaleRequestDto) {

        Sale sale = saleRepository.findById(saleId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "Sale nots exists"));


        ItemSale itemSale = itemSaleRepository.findById(itemSaleId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "ItemSale nots exists"));

        Product product = productRepository.findById(itemSaleRequestDto.getProductId()).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "Product nots exists"));

        if (itemSale.getSale().getId() != saleId) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Error different idSale or idItemSale");
        }

        itemSale.setProduct(product);
        itemSale.setAmount(product.getPrice());
        itemSale.setQuantity(itemSaleRequestDto.getQuantity());

        BigDecimal totalAmount = product.getPrice().multiply(BigDecimal.valueOf(itemSaleRequestDto.getQuantity()));
        itemSale.setTotalAmount(totalAmount);
//        itemSale.setSale(sale);

        ItemSale itemSaleSaved = itemSaleRepository.save(itemSale);

        saleImplementsService.recalculateTotalAmount(sale);

        return itemSaleMapper.toResponse(itemSaleSaved);
    }

    public void deleteItemSaleById(Long saleId, Long itemSaleId) {

        ItemSale itemSale = itemSaleRepository.findById(itemSaleId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "ItemSale nots exists"));

        Sale sale = saleRepository.findById(saleId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "Sale nots exits"));

        if (itemSale.getSale().getId() != sale.getId()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Error different idSale or idItemSale");
        }
        saleImplementsService.recalculateTotalAmount(sale);
        itemSaleRepository.delete(itemSale);
    }
}
