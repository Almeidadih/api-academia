package com.academia.api_academia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstrutorCreateDto {
    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 11, max = 14)
    private String cpf;

    private String telefone;

    private String especialidade;
}
