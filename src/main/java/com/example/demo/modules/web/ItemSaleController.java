package com.example.demo.modules.web;

import com.example.demo.modules.dto.ItemSaleRequestDto;
import com.example.demo.modules.dto.ItemSaleResponseDto;
import com.example.demo.modules.service.ItemSaleImplementsService;
import com.example.demo.modules.service.SaleImplementsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sale/{saleId}/itemSales/")
@Tag(name = "ItemSale", description = "Endpoints for manage ItemSale")
public class ItemSaleController {

    private final ItemSaleImplementsService itemSaleImplementsService;

    public ItemSaleController(ItemSaleImplementsService itemSaleImplementsService) {
        this.itemSaleImplementsService = itemSaleImplementsService;
    }

    @PostMapping()
    public ResponseEntity<ItemSaleResponseDto> insertItemSaleController(@RequestBody ItemSaleRequestDto itemSaleRequestDto,
                                                                        @PathVariable("saleId") Long saleId) {
        ItemSaleResponseDto responseDto = itemSaleImplementsService.insertItemSaleService(itemSaleRequestDto, saleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{itemSaleId}")
    public ResponseEntity<ItemSaleResponseDto> findItemSaleByIdController(@PathVariable("saleId") Long saleId,
                                                                          @PathVariable("itemSaleId") Long itemSaleId) {
        return ResponseEntity.ok(itemSaleImplementsService.findItemSaleById(saleId, itemSaleId));
    }

    @GetMapping
    public ResponseEntity<List<ItemSaleResponseDto>> findAllItemSale(@PathVariable("saleId") Long saleId) {
        return ResponseEntity.ok(itemSaleImplementsService.findAllItemSale(saleId));
    }

    @PutMapping("/{itemSaleId}")
    public ResponseEntity<ItemSaleResponseDto> updateItemSaleByIdController(@PathVariable("saleId") Long saleId,
                                                                            @PathVariable("itemSaleId") Long itemSaleId,
                                                                            @RequestBody ItemSaleRequestDto requestDto) {
        ItemSaleResponseDto responseDto = itemSaleImplementsService.updateItemSaleById(saleId, itemSaleId, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{itemSaleId}")
    public ResponseEntity<?> deleteItemSaleByIdController(@PathVariable("saleId") Long saleId,
                                                          @PathVariable("itemSaleId") Long itemSaleId) {
        itemSaleImplementsService.deleteItemSaleById(saleId, itemSaleId);
        return ResponseEntity.noContent().build();
    }
}
