package com.example.demo.modules.viaceps.controller;

import com.example.demo.modules.viaceps.dto.ViacepResponseDto;
import com.example.demo.modules.viaceps.model.Viacep;
import com.example.demo.modules.viaceps.service.ViacepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/viaceps")
public class ViacepController {

    private final ViacepService viacepService;

    public ViacepController(ViacepService viacepService) {
        this.viacepService = viacepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<ViacepResponseDto> getAddressByCepController(@PathVariable String cep) {
        ViacepResponseDto viacepResponseDto = viacepService.getAddressByCep(cep);
        return ResponseEntity.ok().body(viacepResponseDto);
    }
}
