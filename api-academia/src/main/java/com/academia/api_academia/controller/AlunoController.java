package com.academia.api_academia.controller;

import com.academia.api_academia.dto.AlunoCreateDto;
import com.academia.api_academia.dto.AlunoDto;
import com.academia.api_academia.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/alunos")
@Tag(name = "Alunos", description = "Operações relacionadas a alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @Operation(summary = "Listar alunos", description = "Retorna uma lista paginada de alunos")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de alunos recuperada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado - autenticação necessária", content = @Content)
    })
    @GetMapping
    public org.springframework.data.domain.Page<AlunoDto> getAllAlunos(
            @Parameter(description = "Parâmetros de paginação (page, size, sort)")
            org.springframework.data.domain.Pageable pageable) {
        return alunoService.findAll(pageable);
    }

    @Operation(summary = "Buscar aluno por ID", description = "Retorna um aluno específico pelo seu ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aluno encontrado"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado - autenticação necessária", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> getAlunoById(
            @Parameter(description = "ID do aluno", example = "1")
            @PathVariable Long id) {
        try {
            AlunoDto dto = alunoService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Criar aluno", description = "Cria um novo aluno")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "401", description = "Não autorizado - autenticação necessária", content = @Content)
    })
    @PostMapping
    public ResponseEntity<AlunoDto> createAluno(
            @Parameter(description = "Dados do aluno", required = true)
            @Valid @RequestBody AlunoCreateDto aluno) {
        AlunoDto saved = alunoService.create(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Atualizar aluno", description = "Atualiza os dados de um aluno existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado - autenticação necessária", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<AlunoDto> updateAluno(
            @Parameter(description = "ID do aluno", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Novos dados do aluno", required = true)
            @Valid @RequestBody AlunoCreateDto alunoDetails) {
        try {
            AlunoDto updated = alunoService.update(id, alunoDetails);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remover aluno", description = "Remove um aluno pelo seu ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Aluno removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado - autenticação necessária", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(
            @Parameter(description = "ID do aluno", example = "1")
            @PathVariable Long id) {
        try {
            alunoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
