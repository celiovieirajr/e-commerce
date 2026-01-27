package com.example.demo.modules.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SaleTest {

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Carolina");
        customer.setCpf("45656935999");

        Viacep viacep = new Viacep();
        viacep.setCep("4565656");
        viacep.setLogradouro("Rua um");
        viacep.setComplemento("Casa");
        customer.setAddres(viacep);

        return customer;
    }

    private List<ItemSale> createItemSales() {
        List<ItemSale> itemSales = new ArrayList<>();

        ItemSale item1 = new ItemSale();
        item1.setQuantity(2);
        item1.setAmount(new BigDecimal("50.00"));
        itemSales.add(item1);

        ItemSale item2 = new ItemSale();
        item2.setQuantity(1);
        item2.setAmount(new BigDecimal("30.00"));
        itemSales.add(item2);

        return itemSales;
    }

    @Test
    void testSaleGettersSettersAndTotalAmount() {
        Sale sale = new Sale();
        sale.setId(1L);

        Customer customer = createCustomer();
        sale.setCustomer(customer);
        sale.setCustomerId(customer.getId());

        List<ItemSale> itemSales = createItemSales();
        sale.setItemSale(itemSales);

        BigDecimal totalAmount = itemSales.stream()
                .map(item -> item.getAmount().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        sale.setTotalAmount(totalAmount);

        assertEquals(1L, sale.getId());
        assertNotNull(sale.getCustomer());
        assertEquals(customer.getId(), sale.getCustomerId());
        assertEquals(2, sale.getItemSale().size());
        assertEquals(0, sale.getTotalAmount().compareTo(new BigDecimal("130.00")));
    }
}