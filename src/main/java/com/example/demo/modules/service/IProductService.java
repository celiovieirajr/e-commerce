package com.example.demo.modules.service;

import com.example.demo.modules.dto.ProductRequestDto;
import com.example.demo.modules.dto.ProductResponseDto;

import java.util.List;

public interface IProductService {

    ProductResponseDto insertProduct(ProductRequestDto requestDto);
    ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto);
    ProductResponseDto findProductById(Long id);
    List<ProductResponseDto> findAllProduct();
    void deleteProductById(Long id);
}
