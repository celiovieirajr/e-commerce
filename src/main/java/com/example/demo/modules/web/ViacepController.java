package com.example.demo.modules.web;

import com.example.demo.modules.domain.Viacep;
import com.example.demo.modules.dto.ViacepResponseDto;
import com.example.demo.modules.service.ViacepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("api/v1/address/")
@Tag(name = "Address", description = "Endpoints for manage Address")
public class ViacepController {

    private final ViacepService viacepService;

    public ViacepController(ViacepService viacepService) {
        this.viacepService = viacepService;
    }

    @GetMapping("/{cep}")
    @Operation(summary = "Find Address by cep", description = "Find Address by cep",
               tags = "Addres",
               responses = {
                    @ApiResponse(
                            description = "Sucsess",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Viacep.class))
                            )
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
    })
    public ResponseEntity<ViacepResponseDto> getAddressByCepController(@PathVariable String cep) {
        ViacepResponseDto viacepResponseDto = viacepService.getAddressByCep(cep);
        return ResponseEntity.ok().body(viacepResponseDto);
    }
}
