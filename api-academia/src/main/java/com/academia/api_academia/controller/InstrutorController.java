package com.academia.api_academia.controller;

import com.academia.api_academia.dto.InstrutorCreateDto;
import com.academia.api_academia.dto.InstrutorDto;
import com.academia.api_academia.service.InstrutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instrutores")
public class InstrutorController {

    private final InstrutorService instrutorService;

    public InstrutorController(InstrutorService instrutorService) {
        this.instrutorService = instrutorService;
    }

    @GetMapping
    public org.springframework.data.domain.Page<InstrutorDto> getAllInstrutores(org.springframework.data.domain.Pageable pageable) {
        return instrutorService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrutorDto> getInstrutorById(@PathVariable Long id){
        try {
            InstrutorDto dto = instrutorService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<InstrutorDto> createInstrutor(@Valid @RequestBody InstrutorCreateDto instrutor) {
        InstrutorDto saved = instrutorService.create(instrutor);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstrutorDto> updateInstrutor(@PathVariable Long id,
                                                     @Valid @RequestBody InstrutorCreateDto instrutorDetails) {
        try {
            InstrutorDto updated = instrutorService.update(id, instrutorDetails);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteInstrutor(@PathVariable Long id) {
        try {
            instrutorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
