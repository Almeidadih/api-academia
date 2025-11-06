package com.academia.api_academia.controller;

import com.academia.api_academia.dto.AlunoCreateDto;
import com.academia.api_academia.dto.AlunoDto;
import com.academia.api_academia.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public org.springframework.data.domain.Page<AlunoDto> getAllAlunos(org.springframework.data.domain.Pageable pageable){
        return alunoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> getAlunoById(@PathVariable Long id) {
        try {
            AlunoDto dto = alunoService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AlunoDto> createAluno(@Valid @RequestBody AlunoCreateDto aluno) {
        AlunoDto saved = alunoService.create(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDto> updateAluno(@PathVariable Long id,
                                                @Valid @RequestBody AlunoCreateDto alunoDetails) {
        try {
            AlunoDto updated = alunoService.update(id, alunoDetails);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        try {
            alunoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
