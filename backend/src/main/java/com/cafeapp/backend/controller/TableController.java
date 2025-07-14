package com.cafeapp.backend.controller;

import com.cafeapp.backend.model.TableEntity;
import com.cafeapp.backend.repository.TableRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
@CrossOrigin("*")
public class TableController {

    private final TableRepository repository;

    public TableController(TableRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TableEntity> getAllTables() {
        return repository.findAll();
    }

    @PostMapping
    public TableEntity addTable(@RequestBody TableEntity table) {
        return repository.save(table);
    }

    @PutMapping("/{id}")
    public TableEntity updateTable(@PathVariable Long id, @RequestBody TableEntity table) {
        TableEntity existing = repository.findById(id).orElseThrow();
        existing.setMasaNo(table.getMasaNo());
        existing.setDurum(table.getDurum());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
