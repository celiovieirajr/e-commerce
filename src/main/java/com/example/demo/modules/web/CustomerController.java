package com.example.demo.modules.web;

import com.example.demo.modules.dto.CustomerRequestDto;
import com.example.demo.modules.dto.CustomerResponseDto;
import com.example.demo.modules.dto.ProductRequestDto;
import com.example.demo.modules.service.CustomerImplementsService;
import com.example.demo.modules.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("api/v1/customers/")
@Tag(name = "Customer", description = "Endpoints for manage CUSTOMER")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(CustomerImplementsService customerImplementsService) {
        this.customerService = customerImplementsService;
    }


    @Operation(summary = "Insert CUSTOMER", description = "Insert CUSTOMER",
            tags = "Customer",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(
                                    mediaType = MediaType.ALL_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerRequestDto.class))
                            )
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @PostMapping
    public ResponseEntity<CustomerResponseDto> insertCustomerController(@Valid @RequestBody CustomerRequestDto requestDto) {
        CustomerResponseDto model = customerService.insertCustomer(requestDto);
        return ResponseEntity.ok().body(model);
    }



    @Operation(summary = "Updated CUSTOMER by ID", description = "Updated CUSTOMER by ID",
            tags = "Customer",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.ALL_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ProductRequestDto.class))
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updatedCustomerController(@Valid @PathVariable Long id,
                                                                         @RequestBody CustomerRequestDto requestDto) {
        CustomerResponseDto responseDto = customerService.updatedCustomer(id, requestDto);
        return ResponseEntity.noContent().build();
    }



    @Operation(summary = "Find CUSTOMER by ID", description = "Find CUSTOMER by ID",
            tags = "Customer",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.ALL_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ProductRequestDto.class))
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> findCustomerByIdController(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }



    @Operation(summary = "Find all CUSTOMERS", description = "Find all CUSTOMERS",
            tags = "Customer",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.ALL_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ProductRequestDto.class))
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> findAllCustomerController() {
        return ResponseEntity.ok(customerService.findAllCustomer());
    }



    @Operation(summary = "Delete CUSTOMER by ID", description = "Delete CUSTOMER by ID",
            tags = "Customer",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content(mediaType = MediaType.ALL_VALUE,
                                                                                                      array = @ArraySchema(schema = @Schema(implementation = ProductRequestDto.class)))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerByIdController(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}
