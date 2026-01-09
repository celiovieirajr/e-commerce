package com.example.demo.modules.customers.model;

import com.example.demo.modules.viaceps.model.Viacep;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "customer")
@Data
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Embedded
    private Viacep addres;
}
