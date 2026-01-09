package com.example.demo.modules.products.service;

import com.example.demo.modules.products.dto.ProductRequestDto;
import com.example.demo.modules.products.dto.ProductResponseDto;
import com.example.demo.modules.products.mapper.ProductMapper;
import com.example.demo.modules.products.model.Product;
import com.example.demo.modules.products.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductResponseDto insertProduct(ProductRequestDto requestDto) {
        Product model = mapper.toModel(requestDto);
        Product modelSaved = repository.save(model);

        return mapper.toResponse(modelSaved);
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        Product model = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product nots exits."));

        model.setDescription(requestDto.getDescription());
        model.setPrice(requestDto.getPrice());

        Product modelSaved = repository.save(model);

        return mapper.toResponse(modelSaved);
    }

    public ProductResponseDto findProductById(Long id) {
        return repository.findById(id).map(mapper::toResponse).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product nots exits."));
    }

    public List<ProductResponseDto> findAllProduct() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public void deleteProductById(Long id) {
        Product model = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product nots exits."));

        repository.delete(model);
    }
}
