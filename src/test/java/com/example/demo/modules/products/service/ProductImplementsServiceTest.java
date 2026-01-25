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

    @Test
    void insertProduct_success() {
        ProductRequestDto req = new ProductRequestDto();
        req.setCodProduct("P001");
        req.setDescription("Produto");
        req.setPrice(new BigDecimal("10.00"));

        Product model = new Product();
        model.setCodProduct(req.getCodProduct());
        model.setDescription(req.getDescription());
        model.setPrice(req.getPrice());

        Product saved = new Product();
        saved.setId(1L);
        saved.setCodProduct(req.getCodProduct());
        saved.setDescription(req.getDescription());
        saved.setPrice(req.getPrice());

        ProductResponseDto resp = new ProductResponseDto();
        resp.setId(1L);
        resp.setCodProduct(req.getCodProduct());
        resp.setDescription(req.getDescription());
        resp.setPrice(req.getPrice());

        when(mapper.toModel(req)).thenReturn(model);
        when(repository.existsByCodProductAndIdNot(model.getCodProduct(), model.getId())).thenReturn(false);
        when(repository.save(model)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(resp);

        ProductResponseDto result = service.insertProduct(req);

        assertNotNull(result);
        assertEquals(resp.getId(), result.getId());
        verify(repository).save(model);
    }

    @Test
    void insertProduct_conflict() {
        ProductRequestDto req = new ProductRequestDto();
        req.setCodProduct("P001");
        req.setDescription("Produto");
        req.setPrice(new BigDecimal("10.00"));

        Product model = new Product();
        model.setCodProduct(req.getCodProduct());

        when(mapper.toModel(req)).thenReturn(model);
        when(repository.existsByCodProductAndIdNot(model.getCodProduct(), model.getId())).thenReturn(true);

        ApiException ex = assertThrows(ApiException.class, () -> service.insertProduct(req));
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
        Product p = new Product();
        p.setId(1L);
        p.setCodProduct("P001");
        p.setDescription("Produto");
        p.setPrice(new BigDecimal("5.00"));

        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(1L);
        dto.setCodProduct(p.getCodProduct());
        dto.setDescription(p.getDescription());
        dto.setPrice(p.getPrice());

        when(repository.findAll()).thenReturn(List.of(p));
        when(mapper.toResponse(p)).thenReturn(dto);

        List<ProductResponseDto> all = service.findAllProduct();

        assertEquals(1, all.size());
        assertEquals(dto.getId(), all.get(0).getId());
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
        ProductRequestDto req = new ProductRequestDto();
        req.setCodProduct("P002");
        req.setDescription("Novo");
        req.setPrice(new BigDecimal("20.00"));

        Product existing = new Product();
        existing.setId(3L);
        existing.setCodProduct("OLD");
        existing.setDescription("Velho");
        existing.setPrice(new BigDecimal("1.00"));

        Product saved = new Product();
        saved.setId(3L);
        saved.setCodProduct(req.getCodProduct());
        saved.setDescription(req.getDescription());
        saved.setPrice(req.getPrice());

        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(3L);
        dto.setCodProduct(req.getCodProduct());
        dto.setDescription(req.getDescription());
        dto.setPrice(req.getPrice());

        when(repository.findById(3L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(dto);

        ProductResponseDto result = service.updateProduct(3L, req);

        assertEquals(dto.getId(), result.getId());
        assertEquals(req.getCodProduct(), result.getCodProduct());
    }
}

