package com.academia.api_academia.controller;

import com.academia.api_academia.dto.InstrutorCreateDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InstrutorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnOkForGetInstrutores() throws Exception {
        mockMvc.perform(get("/api/instrutores")
                        .with(httpBasic("admin", "password"))
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateInstrutor() throws Exception {
        InstrutorCreateDto dto = new InstrutorCreateDto("Instrutor", "12345678901", "11999999999", "Musculação");

        mockMvc.perform(post("/api/instrutores")
                        .with(httpBasic("admin", "password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isCreated());
    }
}
