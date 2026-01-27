package com.example.demo.modules.mapper;

import com.example.demo.exception.ApiException;
import com.example.demo.modules.domain.Customer;
import com.example.demo.modules.dto.SaleRequestDto;
import com.example.demo.modules.dto.SaleResponseDto;
import com.example.demo.modules.repository.CustomerRepository;
import com.example.demo.modules.domain.ItemSale;
import com.example.demo.modules.domain.Sale;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SaleMapper {

    private final ItemSaleMapper itemSaleMapper;
    private final CustomerRepository customerRepository;

    public SaleMapper(ItemSaleMapper itemSaleMapper, CustomerRepository customerRepository) {
        this.itemSaleMapper = itemSaleMapper;
        this.customerRepository = customerRepository;
    }

    public Sale toModel(SaleRequestDto dto) {
        Sale sale = new Sale();

        List<ItemSale> itemSales = dto.getItens().stream()
                .map(itemDto -> itemSaleMapper.toModel(itemDto, sale))
                .toList();

        itemSales.forEach(item -> item.setSale(sale));
        sale.setItemSale(itemSales);

        sale.setTotalAmount(BigDecimal.valueOf(0));


        Customer customer = customerRepository.findById(dto.getIdCustomer()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer nots exits"));
        sale.setCustomerId(customer.getId());
        sale.setCustomer(customer);

        return sale;
    }

    public SaleResponseDto toResponse(Sale sale) {
        SaleResponseDto saleResponseDto = new SaleResponseDto();
        saleResponseDto.setSaleId(sale.getId());
        saleResponseDto.setCustomerId(sale.getCustomerId());

        saleResponseDto.setItens(
                sale.getItemSale().stream()
                        .map(itemSaleMapper::toResponse)
                        .toList()
        );

        Customer customer = customerRepository.findById(sale.getCustomerId()).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "Customer nots exits"));

        saleResponseDto.setTotalAmount(sale.getTotalAmount());
        saleResponseDto.setCustomerId(sale.getCustomerId());
        saleResponseDto.setCustomerName(customer.getName());
        saleResponseDto.setDocument(customer.getCpf());

        return saleResponseDto;
    }


}
