package com.academia.api_academia.mapper;

import com.academia.api_academia.dto.AlunoCreateDto;
import com.academia.api_academia.dto.AlunoDto;
import com.academia.api_academia.model.Aluno;

public final class AlunoMapper {
    private AlunoMapper() {}

    public static AlunoDto toDto(Aluno a) {
        if (a == null) return null;
        return new AlunoDto(a.getId(), a.getName(), a.getCpf(), a.getEmail(), a.getTelefone());
    }

    public static Aluno toEntity(AlunoCreateDto dto) {
        if (dto == null) return null;
        Aluno a = new Aluno();
        a.setName(dto.getName());
        a.setCpf(dto.getCpf());
        a.setEmail(dto.getEmail());
        a.setTelefone(dto.getTelefone());
        return a;
    }

    public static void updateEntity(Aluno existing, AlunoCreateDto dto) {
        existing.setName(dto.getName());
        existing.setCpf(dto.getCpf());
        existing.setEmail(dto.getEmail());
        existing.setTelefone(dto.getTelefone());
    }
}
