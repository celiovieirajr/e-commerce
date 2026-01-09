package com.example.demo.modules.products.mapper;

import com.example.demo.modules.products.dto.ProductRequestDto;
import com.example.demo.modules.products.dto.ProductResponseDto;
import com.example.demo.modules.products.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toModel(ProductRequestDto requestDto) {
        Product model = new Product();
        model.setDescription(requestDto.getDescription());
        model.setPrice(requestDto.getPrice());

        return model;
    }

    public ProductResponseDto toResponse(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());

        return responseDto;
    }
}
