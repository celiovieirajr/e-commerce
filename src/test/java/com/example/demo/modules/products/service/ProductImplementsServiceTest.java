package com.example.demo.modules.products.service;

import com.example.demo.exception.ApiException;
import com.example.demo.modules.products.dto.ProductRequestDto;
import com.example.demo.modules.products.dto.ProductResponseDto;
import com.example.demo.modules.products.mapper.ProductMapper;
import com.example.demo.modules.products.model.Product;
import com.example.demo.modules.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductImplementsServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductImplementsService service;

    private ProductRequestDto createProductRequest() {
        ProductRequestDto request = new ProductRequestDto();
        request.setCodProduct("P001");
        request.setDescription("Produto");
        request.setPrice(new BigDecimal("10.00"));

        return  request;
    }

    private Product createProdut(ProductRequestDto request) {
        Product model = new Product();
        model.setId(1L);
        model.setCodProduct(request.getCodProduct());
        model.setDescription(request.getDescription());
        model.setPrice(request.getPrice());

        return model;
    }

    private ProductResponseDto createProductResponse(Product model) {
        ProductResponseDto response = new ProductResponseDto();
        response.setId(model.getId());
        response.setCodProduct(model.getCodProduct());
        response.setDescription(model.getDescription());
        response.setPrice(model.getPrice());

        return response;
    }

    @Test
    void insertProduct_success() {
        ProductRequestDto request = createProductRequest();
        Product model = createProdut(request);
        ProductResponseDto response = createProductResponse(model);

        Product saved = new Product();
        saved.setId(1L);
        saved.setCodProduct(request.getCodProduct());
        saved.setDescription(request.getDescription());
        saved.setPrice(request.getPrice());


        when(mapper.toModel(request)).thenReturn(model);
        when(repository.existsByCodProductAndIdNot(model.getCodProduct(), model.getId())).thenReturn(false);
        when(repository.save(model)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(response);

        ProductResponseDto result = service.insertProduct(request);

        assertNotNull(result);
        assertEquals(response.getId(), result.getId());
        verify(repository).save(model);
    }

    @Test
    void insertProduct_conflict() {
        ProductRequestDto request = createProductRequest();

        Product model = new Product();
        model.setCodProduct(request.getCodProduct());

        when(mapper.toModel(request)).thenReturn(model);
        when(repository.existsByCodProductAndIdNot(model.getCodProduct(), model.getId())).thenReturn(true);

        ApiException ex = assertThrows(ApiException.class, () -> service.insertProduct(request));
        assertTrue(ex.getMessage().contains("Product code already exists") || ex.getStatus().value() == 409);
        verify(repository, never()).save(any());
    }

    @Test
    void findProductById_notFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        ApiException ex = assertThrows(ApiException.class, () -> service.findProductById(99L));
        assertTrue(ex.getMessage().contains("Product not found"));
    }

    @Test
    void findAllProduct_returnsList() {
        ProductRequestDto request = createProductRequest();
        Product model = createProdut(request);
        ProductResponseDto response = createProductResponse(model);

        when(repository.findAll()).thenReturn(List.of(model));
        when(mapper.toResponse(model)).thenReturn(response);

        List<ProductResponseDto> all = service.findAllProduct();

        assertEquals(1, all.size());
        assertEquals(response.getId(), all.get(0).getId());
    }

    @Test
    void deleteProductById_success() {
        Product p = new Product();
        p.setId(2L);
        when(repository.findById(2L)).thenReturn(Optional.of(p));

        service.deleteProductById(2L);

        verify(repository).delete(p);
    }

    @Test
    void updateProduct_success() {
        ProductRequestDto request = createProductRequest();
        Product model = createProdut(request);
        ProductResponseDto response = createProductResponse(model);

        Product saved = new Product();
        saved.setId(3L);
        saved.setCodProduct(request.getCodProduct());
        saved.setDescription(request.getDescription());
        saved.setPrice(request.getPrice());

        when(repository.findById(3L)).thenReturn(Optional.of(model));
        when(repository.save(model)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(response);

        ProductResponseDto result = service.updateProduct(3L, request);

        assertEquals(response.getId(), result.getId());
        assertEquals(request.getCodProduct(), result.getCodProduct());
    }
}

