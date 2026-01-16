package com.example.demo.modules.itensSales.controller;

import com.example.demo.modules.itensSales.dto.ItemSaleRequestDto;
import com.example.demo.modules.itensSales.dto.ItemSaleResponseDto;
import com.example.demo.modules.itensSales.service.ItemSaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/itemSale")
public class ItemSaleController {

    private final ItemSaleService itemSaleService;

    public ItemSaleController(ItemSaleService itemSaleService) {
        this.itemSaleService = itemSaleService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ItemSaleResponseDto> insertItemSaleController(@RequestBody ItemSaleRequestDto itemSaleRequestDto,
                                                                        @PathVariable Long id) {
        ItemSaleResponseDto responseDto = itemSaleService.insertItemSaleService(itemSaleRequestDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemSaleResponseDto> findItemSaleByIdController(@PathVariable Long id) {
        return ResponseEntity.ok(itemSaleService.findItemSaleById(id));
    }

    @GetMapping
    public ResponseEntity<List<ItemSaleResponseDto>> findAllItemSale() {
        return ResponseEntity.ok(itemSaleService.findAllItemSale());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemSaleResponseDto> updateItemSaleByIdController(@PathVariable Long id,
                                                                            @RequestBody ItemSaleRequestDto requestDto) {
        ItemSaleResponseDto responseDto = itemSaleService.updateItemSaleById(id, requestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemSaleByIdController(@PathVariable Long id) {
        itemSaleService.deleteItemSaleById(id);
        return ResponseEntity.noContent().build();
    }
}
