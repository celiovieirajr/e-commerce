package com.example.demo.modules.viaceps.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"cep",
                    "logradouro",
                    "complemento",
                    "bairro",
                    "localidade",
                    "uf",
                    "estado",
                    "regiao",
                    "ibge",
                    "unidade"})
public class ViacepResponseDto {

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
