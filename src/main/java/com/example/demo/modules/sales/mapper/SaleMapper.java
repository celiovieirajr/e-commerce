package com.example.demo.modules.sales.mapper;

import com.example.demo.modules.customers.model.Customer;
import com.example.demo.modules.customers.repository.CustomerRepository;
import com.example.demo.modules.itensSales.mapper.ItemSaleMapper;
import com.example.demo.modules.itensSales.model.ItemSale;
import com.example.demo.modules.sales.dto.SaleRequestDto;
import com.example.demo.modules.sales.dto.SaleResponseDto;
import com.example.demo.modules.sales.model.Sale;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

        if (dto.getItens() != null) {
            List<ItemSale> itemSales = dto.getItens().stream().map(
                    itemDto -> itemSaleMapper.toModel(itemDto, itemDto.getProductId()))
                    .toList();

            itemSales.forEach(item -> item.setSale(sale));
            sale.setItemSale(itemSales);

            BigDecimal totalAmount = itemSales.stream()
                    .map(item -> {
                        BigDecimal price = BigDecimal.ZERO;
                        if (item.getProduct() != null && item.getProduct().getPrice() != null) {
                            price = item.getProduct().getPrice();
                        }

                        BigDecimal quantity = BigDecimal.ZERO;
                        if (item.getQuantity() != null) {
                            quantity = new BigDecimal(item.getQuantity());
                        }

                        return price.multiply(quantity);
                    })
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            sale.setTotalAmount(totalAmount);
        } else {
            sale.setTotalAmount(BigDecimal.ZERO);
        }


        if (dto.getIdCustomer() != null) {
            Customer customer = customerRepository.findById(dto.getIdCustomer()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer nots exits"));

            sale.setCustomer(customer);
        }

        return sale;
    }

    public SaleResponseDto toResponse(Sale sale) {
        SaleResponseDto saleResponseDto = new SaleResponseDto();
        saleResponseDto.setId(sale.getId());

        if (sale.getCustomer() != null) {
            saleResponseDto.setIdCustomer(sale.getCustomer().getId());
        }

        if (sale.getItemSale() != null) {
            saleResponseDto.setItens(
                    sale.getItemSale().stream()
                            .map(itemSaleMapper::toResponse)
                            .toList()
            );
        }

        saleResponseDto.setTotalAmount(sale.getTotalAmount());

        return saleResponseDto;
    }


}
