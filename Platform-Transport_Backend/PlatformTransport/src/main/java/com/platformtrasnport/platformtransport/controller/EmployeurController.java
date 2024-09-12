package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.dto.EmployeurDto;
import com.platformtrasnport.platformtransport.service.EmployeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employeur")
public class EmployeurController {
    @Autowired
    private EmployeurService employeurService;

    @GetMapping("/all")
    public List<EmployeurDto> getAllEmployeurs() {
        return employeurService.getAllEmployeurs();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeurDto> updateEmployeur(@PathVariable("id") Long id, @RequestBody EmployeurDto employeurDto) {
        return employeurService.updateEmployeur(id, employeurDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeur(@PathVariable("id") Long id) {
        if (employeurService.deleteEmployeur(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}