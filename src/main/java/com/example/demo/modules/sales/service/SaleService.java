package com.example.demo.modules.sales.service;

import com.example.demo.exception.ApiException;
import com.example.demo.modules.customers.model.Customer;
import com.example.demo.modules.customers.repository.CustomerRepository;
import com.example.demo.modules.itensSales.model.ItemSale;
import com.example.demo.modules.itensSales.repository.ItemSaleRepository;
import com.example.demo.modules.products.model.Product;
import com.example.demo.modules.products.repository.ProductRepository;
import com.example.demo.modules.sales.dto.SaleRequestDto;
import com.example.demo.modules.sales.dto.SaleResponseDto;
import com.example.demo.modules.sales.mapper.SaleMapper;
import com.example.demo.modules.sales.model.Sale;
import com.example.demo.modules.sales.repository.SaleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class SaleService implements ISaleService{

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final ItemSaleRepository itemSaleRepository;
    private final ProductRepository productRepository;
    private final SaleMapper saleMapper;

    public SaleService(SaleRepository saleRepository, CustomerRepository customerRepository, ItemSaleRepository itemSaleRepository, ProductRepository productRepository, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.itemSaleRepository = itemSaleRepository;
        this.productRepository = productRepository;
        this.saleMapper = saleMapper;
    }

    public SaleResponseDto insertSale(SaleRequestDto saleRequestDto) {
        Sale model = saleMapper.toModel(saleRequestDto);
        Sale modelSaved = saleRepository.save(model);

        return saleMapper.toResponse(modelSaved);
    }

    public SaleResponseDto findSaleById(Long id) {
        return saleRepository.findById(id).map(saleMapper::toResponse).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Sale: " + id + " nots exits"));
    }

    public List<SaleResponseDto> findAllSale() {
        return saleRepository.findAll().stream().map(saleMapper::toResponse).toList();
    }


    public SaleResponseDto updatedSale(Long idVenda, SaleRequestDto dto) {
        Sale sale = saleRepository.findById(idVenda)
                .orElseThrow(() -> new ApiException(NOT_FOUND, "Sale nots exits"));

        Customer customer = customerRepository.findById(dto.getIdCustomer())
                .orElseThrow(() -> new ApiException(NOT_FOUND, "Customer nots exits"));
        sale.setCustomer(customer); // Customer

        if (sale.getItemSale() == null) { // itemSaleIsNull
            sale.setItemSale(new ArrayList<>());
        }

        for (var itemDto : dto.getItens()) {
            ItemSale itemSale = new ItemSale();

            sale.getItemSale().clear();

            Product product = productRepository.findById(itemDto.getProductId()).orElseThrow(
                    () -> new ApiException(NOT_FOUND, "Product nots exits"));
            itemSale.setProduct(product); // setando produto

            int quantity = itemDto.getQuantity();
            BigDecimal amount = product.getPrice();

            itemSale.setQuantity(quantity); // setando quantidade
            itemSale.setAmount(amount); // setando preço unitário do produto

            BigDecimal totalAmount = amount.multiply(BigDecimal.valueOf(quantity));
            itemSale.setTotalAmount(totalAmount);  // setando total

            itemSale.setSale(sale);
            sale.getItemSale().add(itemSale);
        }

        Sale saved = saleRepository.save(sale);
        return saleMapper.toResponse(saved);
    }

    public void deleteSaleById(Long id) {
        saleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Sale " + id + " nots exits"));
    }

}
