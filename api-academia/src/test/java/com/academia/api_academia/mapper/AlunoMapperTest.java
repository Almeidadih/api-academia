package com.academia.api_academia.mapper;

import com.academia.api_academia.dto.AlunoCreateDto;
import com.academia.api_academia.dto.AlunoDto;
import com.academia.api_academia.model.Aluno;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AlunoMapperTest {

    @Test
    void toDto_and_back() {
        Aluno a = new Aluno(1L, "Joao", "12345678901", "joao@example.com", "99999-9999");
        AlunoDto dto = AlunoMapper.toDto(a);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getName()).isEqualTo("Joao");

        AlunoCreateDto createDto = new AlunoCreateDto("Maria", "10987654321", "maria@example.com", "98888-8888");
        Aluno entity = AlunoMapper.toEntity(createDto);
        assertThat(entity).isNotNull();
        assertThat(entity.getName()).isEqualTo("Maria");
        assertThat(entity.getCpf()).isEqualTo("10987654321");
    }
}
