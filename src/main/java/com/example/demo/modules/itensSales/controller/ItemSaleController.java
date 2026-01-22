package com.example.demo.modules.itensSales.controller;

import com.example.demo.modules.itensSales.dto.ItemSaleRequestDto;
import com.example.demo.modules.itensSales.dto.ItemSaleResponseDto;
import com.example.demo.modules.itensSales.service.ItemSaleImplementsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/itemSales")
public class ItemSaleController {

    private final ItemSaleImplementsService itemSaleImplementsService;

    public ItemSaleController(ItemSaleImplementsService itemSaleImplementsService) {
        this.itemSaleImplementsService = itemSaleImplementsService;
    }

    @PostMapping("/{idSale}")
    public ResponseEntity<ItemSaleResponseDto> insertItemSaleController(@RequestBody ItemSaleRequestDto itemSaleRequestDto,
                                                                        @PathVariable Long idSale) {
        ItemSaleResponseDto responseDto = itemSaleImplementsService.insertItemSaleService(itemSaleRequestDto, idSale);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemSaleResponseDto> findItemSaleByIdController(@PathVariable Long id) {
        return ResponseEntity.ok(itemSaleImplementsService.findItemSaleById(id));
    }

    @GetMapping
    public ResponseEntity<List<ItemSaleResponseDto>> findAllItemSale() {
        return ResponseEntity.ok(itemSaleImplementsService.findAllItemSale());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemSaleResponseDto> updateItemSaleByIdController(@PathVariable Long id,
                                                                            @RequestBody ItemSaleRequestDto requestDto) {
        ItemSaleResponseDto responseDto = itemSaleImplementsService.updateItemSaleById(id, requestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemSaleByIdController(@PathVariable Long id) {
        itemSaleImplementsService.deleteItemSaleById(id);
        return ResponseEntity.noContent().build();
    }
}
