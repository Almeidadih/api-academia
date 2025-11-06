package com.academia.api_academia.service;

import com.academia.api_academia.dto.InstrutorCreateDto;
import com.academia.api_academia.dto.InstrutorDto;
import com.academia.api_academia.mapper.InstrutorMapper;
import com.academia.api_academia.model.Instrutor;
import com.academia.api_academia.repository.InstrutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class InstrutorService {

    private final InstrutorRepository repo;

    public InstrutorService(InstrutorRepository repo) {
        this.repo = repo;
    }


    @Transactional(readOnly = true)
    public List<InstrutorDto> findAll() {
        return repo.findAll().stream().map(InstrutorMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<InstrutorDto> findAll(Pageable pageable) {
        return repo.findAll(pageable).map(InstrutorMapper::toDto);
    }

    @Transactional(readOnly = true)
    public InstrutorDto findById(Long id) {
        Instrutor i = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Instrutor not found: " + id));
        return InstrutorMapper.toDto(i);
    }

    @Transactional
    public InstrutorDto create(InstrutorCreateDto dto) {
        Instrutor i = InstrutorMapper.toEntity(dto);
        Instrutor saved = repo.save(i);
        return InstrutorMapper.toDto(saved);
    }

    @Transactional
    public InstrutorDto update(Long id, InstrutorCreateDto dto) {
        Instrutor existing = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Instrutor not found: " + id));
        InstrutorMapper.updateEntity(existing, dto);
        Instrutor updated = repo.save(existing);
        return InstrutorMapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException("Instrutor not found: " + id);
        repo.deleteById(id);
    }
}
