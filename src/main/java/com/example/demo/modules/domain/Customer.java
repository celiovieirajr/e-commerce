package com.example.demo.modules.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "customers")
@Data
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String cpf;

    @Embedded
    private Viacep addres;
}
