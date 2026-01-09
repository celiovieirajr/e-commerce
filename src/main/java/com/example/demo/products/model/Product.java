package com.example.demo.products.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity @Table(name = "products")
@Data
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal price;
}
