package com.academia.api_academia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDto {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String telefone;
}
