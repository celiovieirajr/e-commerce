package com.example.demo.modules.web;

import com.example.demo.modules.dto.SaleRequestDto;
import com.example.demo.modules.dto.SaleResponseDto;
import com.example.demo.modules.service.ISaleService;
import com.example.demo.modules.service.SaleImplementsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final ISaleService saleService;

    public SaleController(SaleImplementsService saleImplementsService) {
        this.saleService = saleImplementsService;
    }

    @PostMapping
    public ResponseEntity<SaleResponseDto> insertSaleController(@RequestBody SaleRequestDto saleRequestDto) {
        SaleResponseDto responseDto = saleService.insertSale(saleRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDto> findSaleByIdController(@PathVariable Long id) {
        SaleResponseDto responseDto = saleService.findSaleById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDto>> findAllSaleController() {
        List<SaleResponseDto> response = saleService.findAllSale();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDto> updateSaleByIdController(@PathVariable Long id,
                                                                    @RequestBody SaleRequestDto saleRequestDto) {
        SaleResponseDto responseDto = saleService.updatedSale(id, saleRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSaleById(@PathVariable Long id) {
        saleService.deleteSaleById(id);
        return ResponseEntity.noContent().build();
    }
}
