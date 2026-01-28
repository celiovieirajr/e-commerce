package com.example.demo.modules.web;

import com.example.demo.modules.domain.Viacep;
import com.example.demo.modules.dto.ProductRequestDto;
import com.example.demo.modules.dto.ProductResponseDto;
import com.example.demo.modules.service.IProductService;
import com.example.demo.modules.service.ProductImplementsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products/")
@Tag(name = "Product", description = "Endpoints for manage Product")
public class ProductController {

    private final IProductService service;

    public ProductController(ProductImplementsService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Insert Product", description = "Insert Product",
            tags = "Product",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(
                                    mediaType = MediaType.ALL_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ProductRequestDto.class))
                            )
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<ProductResponseDto> insertProductController(@Valid @RequestBody ProductRequestDto requestDto) {
        ProductResponseDto response = service.insertProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Updated Product by ID", description = "Updated Product by ID",
            tags = "Product",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.ALL_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ProductRequestDto.class))
                            )
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProductController(@Valid @PathVariable Long id,
                                                                      @RequestBody ProductRequestDto requestDto) {
        return ResponseEntity.ok(service.updateProduct(id, requestDto));
    }

    @Operation(summary = "Find Product by ID", description = "Find Product by ID",
            tags = "Product",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.ALL_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ProductRequestDto.class))
                            )
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProductByIdController(@PathVariable Long id) {
        return ResponseEntity.ok(service.findProductById(id));
    }

    @Operation(summary = "Delete Product by ID", description = "Delete Product by ID",
            tags = "Product",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content(mediaType = MediaType.ALL_VALUE)),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductByIdController(@PathVariable Long id) {
        service.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "FindAll Products", description = "FindAll Products",
            tags = "Product",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.ALL_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ProductRequestDto.class)))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAllProductController() {
        return ResponseEntity.ok(service.findAllProduct());
    }

}
