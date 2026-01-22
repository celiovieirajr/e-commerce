package com.example.demo.modules.itensSales.mapper;

import com.example.demo.exception.ApiException;
import com.example.demo.modules.itensSales.dto.ItemSaleRequestDto;
import com.example.demo.modules.itensSales.dto.ItemSaleResponseDto;
import com.example.demo.modules.itensSales.model.ItemSale;
import com.example.demo.modules.products.mapper.ProductMapper;
import com.example.demo.modules.products.model.Product;
import com.example.demo.modules.products.repository.ProductRepository;
import com.example.demo.modules.sales.model.Sale;
import com.example.demo.modules.sales.repository.SaleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ItemSaleMapper {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SaleRepository saleRepository;


    public ItemSaleMapper(ProductRepository productRepository, ProductMapper productMapper, SaleRepository saleRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.saleRepository = saleRepository;
    }


    public ItemSale toModel(ItemSaleRequestDto itemSaleRequestDto, Sale sale) {

        Product product = productRepository.findById(itemSaleRequestDto.getProductId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Products nots exits"));

        int quantity = itemSaleRequestDto.getQuantity();

        ItemSale itemSale = new ItemSale();
        BigDecimal unitPrice = product.getPrice() == null ? BigDecimal.ZERO : product.getPrice();
        itemSale.setAmount(unitPrice);

        itemSale.setQuantity(quantity);
        BigDecimal total = unitPrice.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);

        itemSale.setTotalAmount(total);
        itemSale.setProduct(product);

        itemSale.setSale(sale);

        return itemSale;
    }

    public ItemSaleResponseDto toResponse(ItemSale itemSale) {
        ItemSaleResponseDto itemSaleResponseDto = new ItemSaleResponseDto();
        itemSaleResponseDto.setId(itemSale.getId());
        itemSaleResponseDto.setAmount(itemSale.getAmount());
        itemSaleResponseDto.setQuantity(itemSale.getQuantity());
        itemSaleResponseDto.setTotalAmount(itemSale.getTotalAmount());
        itemSaleResponseDto.setProductResponseDto(productMapper.toResponse(itemSale.getProduct()));

        return itemSaleResponseDto;
    }


}
