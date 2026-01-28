package com.example.demo.modules.web;

import com.example.demo.modules.dto.ProductRequestDto;
import com.example.demo.modules.dto.ProductResponseDto;
import com.example.demo.modules.service.IProductService;
import com.example.demo.modules.service.ProductImplementsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products/")
@Tag(name = "Product", description = "Endpoints for manage Product")
public class ProductController {

    private final IProductService service;

    public ProductController(ProductImplementsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> insertProductController(@Valid @RequestBody ProductRequestDto requestDto) {
        ProductResponseDto response = service.insertProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProductController(@Valid @PathVariable Long id,
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
