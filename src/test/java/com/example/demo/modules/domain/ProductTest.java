package com.example.demo.modules.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

class ProductTest {

    @Test
    void lombokGettersSettersAndValues() {
        Product p = new Product();
        p.setCodProduct("P-001");
        p.setDescription("Produto 1");
        p.setPrice(new BigDecimal("123.45"));

        assertEquals("P-001", p.getCodProduct());
        assertEquals("Produto 1", p.getDescription());
        assertEquals(new BigDecimal("123.45"), p.getPrice());
    }

    @Test
    void jpaAnnotationsPresent() {
        Class<Product> clazz = Product.class;
        assertTrue(clazz.isAnnotationPresent(Entity.class), "@Entity ausente");
        Table table = clazz.getAnnotation(Table.class);
        assertNotNull(table, "@Table ausente");
        assertEquals("products", table.name(), "nome da tabela incorreto");
    }
}

