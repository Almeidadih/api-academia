package com.academia.api_academia.controller;

import com.academia.api_academia.dto.AlunoCreateDto;
import com.academia.api_academia.dto.AlunoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AlunoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnOkForGetAlunos() throws Exception {
        // Primeiro criamos um aluno para garantir que há dados
        createSampleAluno();

        mockMvc.perform(get("/api/alunos")
                        .with(httpBasic("admin", "password"))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[*].name").value("Teste"))
                .andExpect(jsonPath("$.content[*].email").value("teste@example.com"));
    }

    @Test
    void shouldCreateAluno() throws Exception {
        AlunoCreateDto dto = new AlunoCreateDto("Teste", "12345678901", "teste@example.com", "11999999999");

        mockMvc.perform(post("/api/alunos")
                        .with(httpBasic("admin", "password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(dto.getName()))
                .andExpect(jsonPath("$.email").value(dto.getEmail()));
    }

    @Test
    void shouldFailToCreateAlunoWithInvalidData() throws Exception {
        AlunoCreateDto dto = new AlunoCreateDto("", "", "invalidemail", "");

        mockMvc.perform(post("/api/alunos")
                        .with(httpBasic("admin", "password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors.name").value("must not be blank"))
                .andExpect(jsonPath("$.errors.cpf").value("must not be blank"))
                .andExpect(jsonPath("$.errors.email").value("must be a well-formed email address"));
    }

    @Test
    void shouldUpdateAluno() throws Exception {
        // Primeiro criamos um aluno
        MvcResult createResult = createSampleAluno();
        AlunoDto createdAluno = objectMapper.readValue(
                createResult.getResponse().getContentAsString(),
                AlunoDto.class
        );

        // Agora atualizamos o aluno
        AlunoCreateDto updateDto = new AlunoCreateDto(
                "Teste Atualizado",
                "98765432101",
                "atualizado@example.com",
                "11988888888"
        );

        mockMvc.perform(put("/api/alunos/" + createdAluno.getId())
                        .with(httpBasic("admin", "password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updateDto.getName()))
                .andExpect(jsonPath("$.email").value(updateDto.getEmail()));
    }

    @Test
    void shouldReturn404WhenUpdatingNonExistentAluno() throws Exception {
        AlunoCreateDto dto = new AlunoCreateDto("Teste", "12345678901", "teste@example.com", "11999999999");

        mockMvc.perform(put("/api/alunos/99999")
                        .with(httpBasic("admin", "password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAluno() throws Exception {
        // Primeiro criamos um aluno
        MvcResult createResult = createSampleAluno();
        AlunoDto createdAluno = objectMapper.readValue(
                createResult.getResponse().getContentAsString(),
                AlunoDto.class
        );

        // Deletamos o aluno
        mockMvc.perform(delete("/api/alunos/" + createdAluno.getId())
                        .with(httpBasic("admin", "password"))
                )
                .andExpect(status().isNoContent());

        // Verificamos se foi realmente deletado
        mockMvc.perform(get("/api/alunos/" + createdAluno.getId())
                        .with(httpBasic("admin", "password"))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldRequireAuthentication() throws Exception {
        mockMvc.perform(get("/api/alunos"))
                .andExpect(status().isUnauthorized());
    }

    private MvcResult createSampleAluno() throws Exception {
        AlunoCreateDto dto = new AlunoCreateDto("Teste", "12345678901", "teste@example.com", "11999999999");

        return mockMvc.perform(post("/api/alunos")
                        .with(httpBasic("admin", "password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isCreated())
                .andReturn();
    }
}
