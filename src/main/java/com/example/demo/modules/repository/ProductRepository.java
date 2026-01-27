package com.example.demo.modules.repository;

import com.example.demo.modules.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCodProductAndIdNot(String codProducts, Long id);
}
