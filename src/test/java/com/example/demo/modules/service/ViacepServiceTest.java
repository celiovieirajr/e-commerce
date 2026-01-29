package com.example.demo.modules.service;

import com.example.demo.modules.domain.Viacep;
import com.example.demo.modules.dto.ViacepResponseDto;
import com.example.demo.modules.mapper.ViacepMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ViacepServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private ViacepMapper viacepMapper;

    @InjectMocks
    private ViacepService viacepService;

    @Test
    void findAddresByCepTeste_success() {
        String cep = "12345678";

        Viacep viacep = new Viacep();
        viacep.setCep(cep);
        viacep.setLogradouro("Rua Exemplo");

        ViacepResponseDto expected = new ViacepResponseDto();
        expected.setCep(cep);
        expected.setLogradouro("Rua Exemplo");

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(cep + "/json")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Viacep.class)).thenReturn(Mono.just(viacep));

        when(viacepMapper.toResponse(viacep)).thenReturn(expected);

        ViacepResponseDto result = viacepService.getAddressByCep(cep);

        assertEquals(expected.getCep(), result.getCep());
        assertEquals(expected.getLogradouro(), result.getLogradouro());
    }
}