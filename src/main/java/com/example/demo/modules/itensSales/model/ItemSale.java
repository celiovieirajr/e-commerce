package com.example.demo.modules.itensSales.model;

import com.example.demo.modules.products.model.Product;
import com.example.demo.modules.sales.model.Sale;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "itemSale")
@Data

public class ItemSale {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    private Product product;

    private Integer quantity;
    private BigDecimal amount;
    private BigDecimal totalAmount;
}

