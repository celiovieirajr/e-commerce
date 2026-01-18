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
import com.example.demo.modules.sales.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ItemSaleService {

    private final ProductRepository productRepository;
    private final ItemSaleRepository itemSaleRepository;
    private final ItemSaleMapper itemSaleMapper;
    private final SaleService saleService;
    private final SaleRepository saleRepository;

    public ItemSaleService(ItemSaleRepository itemSaleRepository, ItemSaleMapper itemSaleMapper, ProductRepository productRepository, SaleService saleService, SaleRepository saleRepository) {
        this.itemSaleRepository = itemSaleRepository;
        this.itemSaleMapper = itemSaleMapper;
        this.productRepository = productRepository;
        this.saleService = saleService;
        this.saleRepository = saleRepository;
    }

    public ItemSaleResponseDto insertItemSaleService(ItemSaleRequestDto itemSaleRequestDto, Long idSale) {

        ItemSale itemSale = itemSaleMapper.toModel(itemSaleRequestDto, idSale);
        ItemSale itemSaleSaved = itemSaleRepository.save(itemSale);

        if (idSale != null) {
            Sale sale = saleRepository.findById(idSale).orElseThrow(
                    () -> new ApiException(HttpStatus.NOT_FOUND, "Sale nots exits"));

            saleService.recalculateTotalAmount(sale);
            saleRepository.save(sale);
        }

        return itemSaleMapper.toResponse(itemSaleSaved);
    }

    public ItemSaleResponseDto findItemSaleById(Long id) {
        return itemSaleRepository.findById(id).map(itemSaleMapper::toResponse).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemSale nots exits"));
    }

    public List<ItemSaleResponseDto> findAllItemSale() {
        return itemSaleRepository.findAll().stream().map(itemSaleMapper::toResponse).toList();
    }

    public ItemSaleResponseDto updateItemSaleById(Long id, ItemSaleRequestDto itemSaleRequestDto) {
        ItemSale model = itemSaleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemSales nots exits"));

        model.setQuantity(itemSaleRequestDto.getQuantity());

        if (model.getProduct() != null) {
            Product product = productRepository.findById(itemSaleRequestDto.getProductId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Products nots exits"));
            model.setProduct(product);
        }

        ItemSale modelSaved = itemSaleRepository.save(model);

        return itemSaleMapper.toResponse(modelSaved);
    }

    public void deleteItemSaleById(Long id) {
        itemSaleRepository.delete(itemSaleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemSale nots exits")));
    }
}
