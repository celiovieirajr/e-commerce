package com.example.demo.modules.products.repository;

import com.example.demo.modules.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
