package com.example.demo.modules.products.controller;

import com.example.demo.modules.products.dto.ProductRequestDto;
import com.example.demo.modules.products.dto.ProductResponseDto;
import com.example.demo.modules.products.service.IProductService;
import com.example.demo.modules.products.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final IProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> insertProductController(@RequestBody ProductRequestDto requestDto) {
        ProductResponseDto response = service.insertProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProductController(@PathVariable Long id,
                                                                      @RequestBody ProductRequestDto requestDto) {
        return ResponseEntity.ok(service.updateProduct(id, requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProductByIdController(@PathVariable Long id) {
        return ResponseEntity.ok(service.findProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAllProductController() {
        return ResponseEntity.ok(service.findAllProduct());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductByIdController(@PathVariable Long id) {
        service.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
