package com.example.demo.modules.mapper;

import com.example.demo.modules.domain.Product;
import com.example.demo.modules.dto.ProductRequestDto;
import com.example.demo.modules.dto.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toModel(ProductRequestDto requestDto) {
        Product model = new Product();
        model.setCodProduct(requestDto.getCodProduct());
        model.setDescription(requestDto.getDescription());
        model.setPrice(requestDto.getPrice());

        return model;
    }

    public ProductResponseDto toResponse(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setCodProduct(product.getCodProduct());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());

        return responseDto;
    }
}
