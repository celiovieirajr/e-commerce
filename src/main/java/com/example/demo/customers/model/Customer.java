package com.example.demo.customers.model;

import com.example.demo.viaceps.model.Viacep;
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
