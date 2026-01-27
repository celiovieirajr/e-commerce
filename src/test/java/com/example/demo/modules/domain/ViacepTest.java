package com.example.demo.modules.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViacepTest {

    @Test
    void testViacepGettersAndSetters() {
        Viacep viacep = new Viacep();

        viacep.setCep("12345678");
        viacep.setLogradouro("Rua das Flores");
        viacep.setComplemento("Apto 101");
        viacep.setUnidade("SP");
        viacep.setBairro("Centro");
        viacep.setLocalidade("São Paulo");
        viacep.setUf("SP");
        viacep.setEstado("São Paulo");
        viacep.setRegiao("Sudeste");
        viacep.setIbge("123456");

        assertEquals("12345678", viacep.getCep());
        assertEquals("Rua das Flores", viacep.getLogradouro());
        assertEquals("Apto 101", viacep.getComplemento());
        assertEquals("SP", viacep.getUnidade());
        assertEquals("Centro", viacep.getBairro());
        assertEquals("São Paulo", viacep.getLocalidade());
        assertEquals("SP", viacep.getUf());
        assertEquals("São Paulo", viacep.getEstado());
        assertEquals("Sudeste", viacep.getRegiao());
        assertEquals("123456", viacep.getIbge());
    }
}