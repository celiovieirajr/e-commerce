package com.example.demo.modules.web;

import com.example.demo.modules.domain.Viacep;
import com.example.demo.modules.dto.SaleRequestDto;
import com.example.demo.modules.dto.SaleResponseDto;
import com.example.demo.modules.service.ISaleService;
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
@RequestMapping("api/v1/sales/")
@Tag(name = "Sale", description = "Endpoints for manage Sale")
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
    @Operation(summary = "Find Sale by id", description = "Find Sale by id",
            tags = "Sale",
            responses = {
                    @ApiResponse(
                            description = "Sucsess",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = SaleRequestDto.class))
                            )
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
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
