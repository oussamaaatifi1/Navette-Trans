package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.dto.EmployeDto;
import com.platformtrasnport.platformtransport.service.impl.EmployeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeController {

    private final EmployeServiceImpl employeService;

    public EmployeController(EmployeServiceImpl employeService) {
        this.employeService = employeService;
    }

    @GetMapping
    public List<EmployeDto> getAllUtilisateurs() {
        return employeService.getAllEmployes();
    }

    @PostMapping
    public ResponseEntity<EmployeDto> createUtilisateur(@RequestBody EmployeDto employeDto) {
        EmployeDto createdEmploye = employeService.createEmploye(employeDto);
        return new ResponseEntity<>(createdEmploye, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeDto> getUtilisateurById(@PathVariable("id") Long id) {
        Optional<EmployeDto> employeDto = employeService.getEmployeById(id);
        return employeDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeDto> updateUtilisateur(@PathVariable("id") Long id, @RequestBody EmployeDto employeDto) {
        Optional<EmployeDto> updatedEmploye = employeService.updateUtilisateur(id, employeDto);
        return updatedEmploye.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable("id") Long id) {
        if (employeService.deleteUtilisateur(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
