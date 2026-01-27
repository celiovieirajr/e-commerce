package com.example.demo.modules.service;

import com.example.demo.exception.ApiException;
import com.example.demo.modules.domain.Customer;
import com.example.demo.modules.repository.CustomerRepository;
import com.example.demo.modules.domain.ItemSale;
import com.example.demo.modules.repository.ItemSaleRepository;
import com.example.demo.modules.domain.Product;
import com.example.demo.modules.repository.ProductRepository;
import com.example.demo.modules.dto.SaleRequestDto;
import com.example.demo.modules.dto.SaleResponseDto;
import com.example.demo.modules.mapper.SaleMapper;
import com.example.demo.modules.domain.Sale;
import com.example.demo.modules.repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class SaleImplementsService implements ISaleService{

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final ItemSaleRepository itemSaleRepository;
    private final ProductRepository productRepository;
    private final SaleMapper saleMapper;

    public SaleImplementsService(SaleRepository saleRepository, CustomerRepository customerRepository, ItemSaleRepository itemSaleRepository, ProductRepository productRepository, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.itemSaleRepository = itemSaleRepository;
        this.productRepository = productRepository;
        this.saleMapper = saleMapper;
    }

    public SaleResponseDto insertSale(SaleRequestDto saleRequestDto) {
        Sale model = saleMapper.toModel(saleRequestDto);
        recalculateTotalAmount(model);
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
        sale.setCustomerId(customer.getId());

        if (sale.getItemSale() == null) {
            throw new ApiException(BAD_REQUEST, "Depedency itensSales nots exits");
        }

        sale.getItemSale().clear();

        for (var itemDto : dto.getItens()) {
            ItemSale itemSale = new ItemSale();

            List<ItemSale> listItens = sale.getItemSale();

            Product product = productRepository.findById(itemDto.getProductId()).orElseThrow(
                    () -> new ApiException(NOT_FOUND, "Product nots exits"));
            itemSale.setProduct(product);

            int quantity = itemDto.getQuantity();
            BigDecimal amount = product.getPrice();

            itemSale.setQuantity(quantity);
            itemSale.setAmount(amount);

            BigDecimal totalAmount = amount.multiply(BigDecimal.valueOf(quantity));
            itemSale.setTotalAmount(totalAmount);

            itemSale.setSale(sale);
            listItens.add(itemSale);
        }

        Sale saved = recalculateTotalAmount(sale);
        return saleMapper.toResponse(saved);
    }

    public void deleteSaleById(Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Sale " + id + " nots exits"));

        saleRepository.delete(sale);
    }

    public Sale recalculateTotalAmount (Sale sale) {
        BigDecimal total = sale.getItemSale() == null
                ? BigDecimal.ZERO
                : sale.getItemSale().stream()
                .map(ItemSale::getTotalAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        sale.setTotalAmount(total);
        saleRepository.save(sale);

        return sale;
    }

}
