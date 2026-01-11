package com.example.demo.modules.sales.service;

import com.example.demo.modules.customers.model.Customer;
import com.example.demo.modules.customers.repository.CustomerRepository;
import com.example.demo.modules.itensSales.model.ItemSale;
import com.example.demo.modules.itensSales.repository.ItemSaleRepository;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

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
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale: " + id + " nots exits"));
    }

    public List<SaleResponseDto> findAllSale() {
        return saleRepository.findAll().stream().map(saleMapper::toResponse).toList();
    }


    public SaleResponseDto updatedSale(Long id, SaleRequestDto saleRequestDto) {
        Sale model = saleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale: " + id + " not found"));

        Customer customer = customerRepository.findById(saleRequestDto.getIdCustomer()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer " + saleRequestDto.getIdCustomer() + " not found"));
        model.setCustomer(customer);

        List<ItemSale> itemSales = saleRequestDto.getItens().stream().map(itemDto -> {
            ItemSale item = new ItemSale();

            item.setProduct(productRepository.findById(itemDto.getProductId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product " + itemDto.getProductId() + " not found")));
            item.setQuantity(itemDto.getQuantity());
            item.setAmount(itemDto.getAmount());

            BigDecimal totalItem = itemDto.getAmount().multiply(BigDecimal.valueOf(itemDto.getQuantity()));
            item.setTotalAmount(totalItem);

            item.setSale(model);
            return item;
        }).collect(Collectors.toList());

        model.setItemSale(itemSales);

        BigDecimal totalSale = BigDecimal.ZERO;

        for (ItemSale item: itemSales) {
            totalSale = totalSale.add(item.getAmount());
        }

        model.setTotalAmount(totalSale);

        Sale response = saleRepository.save(model);
        return saleMapper.toResponse(response);
    }

    public void deleteSaleById(Long id) {
        saleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale " + id + " nots exits"));
    }

}
