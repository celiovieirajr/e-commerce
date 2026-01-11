package com.example.demo.modules.sales.repository;

import com.example.demo.modules.sales.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
