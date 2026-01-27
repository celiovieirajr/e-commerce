package com.example.demo.modules.service;

import com.example.demo.modules.dto.ViacepResponseDto;
import com.example.demo.exception.ViacepException;
import com.example.demo.modules.mapper.ViacepMapper;
import com.example.demo.modules.domain.Viacep;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class ViacepService {

    private final WebClient webClient;
    private final ViacepMapper viacepMapper;

    public ViacepService(WebClient webClient, ViacepMapper viacepMapper) {
        this.webClient = webClient;
        this.viacepMapper = viacepMapper;
    }

    public ViacepResponseDto getAddressByCep(String cep) {

        try {
            Viacep viacep =  webClient.get()
                    .uri(cep + "/json")
                    .retrieve()
                    .bodyToMono(Viacep.class)
                    .block();

            if (viacep == null) {
                throw new ViacepException(HttpStatus.NOT_FOUND, "CEP não encontrado");
            }

            return viacepMapper.toResponse(viacep);
        } catch (WebClientResponseException e) {
            HttpStatus status = HttpStatus.resolve(e.getStatusCode().value());
            if (status == null) status = HttpStatus.BAD_GATEWAY;
            throw  new ViacepException(status, "Erro ao consultar Cep: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            throw new ViacepException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno: " + e.getMessage());
        }
    }
}
