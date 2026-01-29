package com.example.demo.modules.web;

import com.example.demo.modules.dto.CustomerRequestDto;
import com.example.demo.modules.dto.ItemSaleRequestDto;
import com.example.demo.modules.dto.ItemSaleResponseDto;
import com.example.demo.modules.service.ItemSaleImplementsService;
import com.example.demo.modules.service.SaleImplementsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sale/{saleId}/itemSales/")
@Tag(name = "ItemSale", description = "Endpoints for manage itemSale")
public class ItemSaleController {

    private final ItemSaleImplementsService itemSaleImplementsService;

    public ItemSaleController(ItemSaleImplementsService itemSaleImplementsService) {
        this.itemSaleImplementsService = itemSaleImplementsService;
    }


    @Operation(summary = "Insert IntemSale by SALE", description = "Insert IntemSale",
            tags = "ItemSale",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ItemSaleRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @PostMapping()
    public ResponseEntity<ItemSaleResponseDto> insertItemSaleController(@RequestBody ItemSaleRequestDto itemSaleRequestDto,
                                                                        @PathVariable("saleId") Long saleId) {
        ItemSaleResponseDto responseDto = itemSaleImplementsService.insertItemSaleService(itemSaleRequestDto, saleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }



    @Operation(summary = "Find IntemSale by ID_SALE and ID_SALE_ITEM", description = "Find IntemSale by ID_SALE and ID_SALE_ITEM",
            tags = "ItemSale",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ItemSaleRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping("/{itemSaleId}")
    public ResponseEntity<ItemSaleResponseDto> findItemSaleByIdController(@PathVariable("saleId") Long saleId,
                                                                          @PathVariable("itemSaleId") Long itemSaleId) {
        return ResponseEntity.ok(itemSaleImplementsService.findItemSaleById(saleId, itemSaleId));
    }


    @Operation(summary = "FindAll IntemSales by ID_SALE", description = "FindAll IntemSales by ID_SALE",
            tags = "ItemSale",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.ALL_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ItemSaleResponseDto.class))
                            )
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping
    public ResponseEntity<List<ItemSaleResponseDto>> findAllItemSale(@PathVariable("saleId") Long saleId) {
        return ResponseEntity.ok(itemSaleImplementsService.findAllItemSale(saleId));
    }



    @Operation(summary = "Updated IntemSales by ID_SALE and ID_SALE_ITEM", description = "Updated IntemSales by ID_SALE and ID_SALE_ITEM",
            tags = "ItemSale",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ItemSaleRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @PutMapping("/{itemSaleId}")
    public ResponseEntity<ItemSaleResponseDto> updateItemSaleByIdController(@PathVariable("saleId") Long saleId,
                                                                            @PathVariable("itemSaleId") Long itemSaleId,
                                                                            @RequestBody ItemSaleRequestDto requestDto) {
        ItemSaleResponseDto responseDto = itemSaleImplementsService.updateItemSaleById(saleId, itemSaleId, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }



    @Operation(summary = "Deleted IntemSales by ID_SALE and ID_ITEM_SALE", description = "Deleted IntemSales by ID_SALE and ID_ITEM_SALE",
            tags = "ItemSale",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @DeleteMapping("/{itemSaleId}")
    public ResponseEntity<?> deleteItemSaleByIdController(@PathVariable("saleId") Long saleId,
                                                          @PathVariable("itemSaleId") Long itemSaleId) {
        itemSaleImplementsService.deleteItemSaleById(saleId, itemSaleId);
        return ResponseEntity.noContent().build();
    }
}
