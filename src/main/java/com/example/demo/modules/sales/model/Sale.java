package com.example.demo.modules.sales.model;

import com.example.demo.modules.customers.model.Customer;
import com.example.demo.modules.itensSales.model.ItemSale;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
public class Sale {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemSale> itemSale;

    private BigDecimal totalAmount;
}
