package com.example.demo.modules.itensSales.mapper;

import com.example.demo.modules.itensSales.dto.ItemSaleRequestDto;
import com.example.demo.modules.itensSales.dto.ItemSaleResponseDto;
import com.example.demo.modules.itensSales.model.ItemSale;
import com.example.demo.modules.products.mapper.ProductMapper;
import com.example.demo.modules.products.model.Product;
import com.example.demo.modules.products.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ItemSaleMapper {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public ItemSaleMapper(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ItemSale toModel(ItemSaleRequestDto itemSaleRequestDto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Products nots exits"));

        ItemSale itemSale = new ItemSale();
        itemSale.setAmount(itemSaleRequestDto.getAmount());
        itemSale.setQuantity(itemSaleRequestDto.getQuantity());
        itemSale.setTotalAmount(itemSaleRequestDto.getTotalAmount());
        itemSale.setProduct(product);

        return itemSale;
    }

    public ItemSaleResponseDto toResponse(ItemSale itemSale) {
        ItemSaleResponseDto itemSaleResponseDto = new ItemSaleResponseDto();
        itemSaleResponseDto.setId(itemSale.getId());
        itemSaleResponseDto.setAmount(itemSale.getAmount());
        itemSaleResponseDto.setQuantity(itemSale.getQuantity());
        itemSaleResponseDto.setTotalAmount(itemSale.getTotalAmount());
        itemSaleResponseDto.setProductResponseDto(productMapper.toResponse(itemSale.getProduct()));

        return itemSaleResponseDto;
    }


}
