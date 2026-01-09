package com.example.demo.modules.viaceps.service;

import com.example.demo.modules.viaceps.model.Viacep;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ViacepService {

    private final WebClient webClient;

    public ViacepService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Viacep getAddressByCep(String cep) {
        return  webClient.get()
                .uri(cep + "/json")
                .retrieve()
                .bodyToMono(Viacep.class)
                .block();
    }
}
