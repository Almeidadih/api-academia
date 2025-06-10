package com.academia.api_academia.controller;

import com.academia.api_academia.model.Aluno;
import com.academia.api_academia.repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> getAllAlunos(){
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno.map(ResponseEntity::ok)
                .orElseGet(()->
                        ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@Valid
                                             @RequestBody Aluno aluno) {
        Aluno saveAluno = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveAluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id,
                                             @Valid
                                             @RequestBody Aluno alunoDetails) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            Aluno existingAluno = aluno.get();
            existingAluno.setName(alunoDetails.getName());
            existingAluno.setCpf(alunoDetails.getCpf());
            existingAluno.setEmail(alunoDetails.getEmail());

            existingAluno.setTelefone(alunoDetails.getTelefone());
            return
                    ResponseEntity.ok(alunoRepository.save(existingAluno));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }
}
