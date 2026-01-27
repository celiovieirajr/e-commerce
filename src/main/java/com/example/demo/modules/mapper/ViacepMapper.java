package com.example.demo.modules.mapper;

import com.example.demo.modules.dto.ViacepResponseDto;
import com.example.demo.modules.domain.Viacep;
import org.springframework.stereotype.Component;

@Component
public class ViacepMapper {

    public ViacepResponseDto toResponse(Viacep viacep) {
        ViacepResponseDto responseDto = new ViacepResponseDto();
        responseDto.setCep(viacep.getCep());
        responseDto.setLogradouro(viacep.getLogradouro());
        responseDto.setComplemento(viacep.getComplemento());
        responseDto.setUnidade(viacep.getUnidade());
        responseDto.setBairro(viacep.getBairro());
        responseDto.setLocalidade(viacep.getLocalidade());
        responseDto.setUf(viacep.getUf());
        responseDto.setEstado(viacep.getEstado());
        responseDto.setRegiao(viacep.getRegiao());
        responseDto.setIbge(viacep.getIbge());

        return responseDto;
    }
}
