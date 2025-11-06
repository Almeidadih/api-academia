package com.academia.api_academia.mapper;

import com.academia.api_academia.dto.InstrutorCreateDto;
import com.academia.api_academia.dto.InstrutorDto;
import com.academia.api_academia.model.Instrutor;

public final class InstrutorMapper {
    private InstrutorMapper() {}

    public static InstrutorDto toDto(Instrutor i) {
        if (i == null) return null;
        return new InstrutorDto(i.getId(), i.getName(), i.getCpf(), i.getTelefone(), i.getEspecialidade());
    }

    public static Instrutor toEntity(InstrutorCreateDto dto) {
        if (dto == null) return null;
        Instrutor i = new Instrutor();
        i.setName(dto.getName());
        i.setCpf(dto.getCpf());
        i.setTelefone(dto.getTelefone());
        i.setEspecialidade(dto.getEspecialidade());
        return i;
    }

    public static void updateEntity(Instrutor existing, InstrutorCreateDto dto) {
        existing.setName(dto.getName());
        existing.setCpf(dto.getCpf());
        existing.setTelefone(dto.getTelefone());
        existing.setEspecialidade(dto.getEspecialidade());
    }
}
