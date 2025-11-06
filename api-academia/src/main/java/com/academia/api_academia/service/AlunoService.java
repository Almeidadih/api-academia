package com.academia.api_academia.service;

import com.academia.api_academia.dto.AlunoCreateDto;
import com.academia.api_academia.dto.AlunoDto;
import com.academia.api_academia.mapper.AlunoMapper;
import com.academia.api_academia.model.Aluno;
import com.academia.api_academia.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository repo;

    public AlunoService(AlunoRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<AlunoDto> findAll() {
        return repo.findAll().stream().map(AlunoMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AlunoDto findById(Long id) {
        Aluno a = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Aluno not found: " + id));
        return AlunoMapper.toDto(a);
    }

    @Transactional
    public AlunoDto create(AlunoCreateDto dto) {
        Aluno a = AlunoMapper.toEntity(dto);
        Aluno saved = repo.save(a);
        return AlunoMapper.toDto(saved);
    }

    @Transactional
    public AlunoDto update(Long id, AlunoCreateDto dto) {
        Aluno existing = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Aluno not found: " + id));
        AlunoMapper.updateEntity(existing, dto);
        Aluno updated = repo.save(existing);
        return AlunoMapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException("Aluno not found: " + id);
        repo.deleteById(id);
    }
}
