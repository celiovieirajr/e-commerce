package com.example.demo.modules.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ItemSaleTest {

    @Test
    void gettersSettersAndSubtotal() {
        ItemSale item = new ItemSale();
        item.setQuantity(3);
        item.setAmount(new BigDecimal("10.50"));

        assertEquals(3, item.getQuantity());
        assertNotNull(item.getAmount());
        assertEquals(0, item.getAmount().compareTo(new BigDecimal("10.50")));

        BigDecimal expectedSubtotal = item.getAmount().multiply(BigDecimal.valueOf(item.getQuantity()));
        assertEquals(0, expectedSubtotal.compareTo(new BigDecimal("31.50")));
    }
}