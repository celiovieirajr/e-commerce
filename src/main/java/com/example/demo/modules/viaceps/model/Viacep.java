package com.example.demo.modules.viaceps.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Viacep {

    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ibge;
}
