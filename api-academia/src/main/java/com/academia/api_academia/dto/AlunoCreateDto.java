package com.academia.api_academia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoCreateDto {
    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 11, max = 14)
    private String cpf;

    @NotBlank
    @Email
    private String email;

    private String telefone;
}
