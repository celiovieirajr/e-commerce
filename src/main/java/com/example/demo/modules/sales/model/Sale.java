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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "customer_id", insertable = false, updatable = false)
    private Long customerId;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemSale> itemSale;

    private BigDecimal totalAmount;
}
