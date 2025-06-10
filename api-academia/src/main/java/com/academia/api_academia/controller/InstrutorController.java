package com.academia.api_academia.controller;

import com.academia.api_academia.model.Instrutor;
import com.academia.api_academia.repository.InstrutorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instrutores")
public class InstrutorController {
    @Autowired
    private InstrutorRepository instrutorRepository;

    @GetMapping
    public List<Instrutor> getAllInstrutores() {
        return instrutorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instrutor> getInstrutorById(@PathVariable Long id){
        Optional<Instrutor> instrutor = instrutorRepository.findById(id);
        return instrutor.map(ResponseEntity::ok)
                .orElseGet(()->
                        ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Instrutor> createInstrutor(@Valid @RequestBody Instrutor instrutor) {
        Instrutor savedInstrutor = instrutorRepository.save(instrutor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInstrutor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instrutor> updateInstrutor(@PathVariable Long id,
                                                     @Valid @RequestBody Instrutor instrutorDetails) {
        Optional<Instrutor> instrutor = instrutorRepository.findById(id);
        if (instrutor.isPresent()) {
            Instrutor existingInstrutor = instrutor.get();

            existingInstrutor.setName(instrutorDetails.getName());
            existingInstrutor.setCpf(instrutorDetails.getCpf());
            existingInstrutor.setEspecialidade(instrutorDetails.getEspecialidade());
            existingInstrutor.setTelefone(instrutorDetails.getTelefone());

            return ResponseEntity.ok(instrutorRepository.save(existingInstrutor));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteInstrutor(@PathVariable Long id) {
        if (instrutorRepository.existsById(id)) {
            instrutorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
