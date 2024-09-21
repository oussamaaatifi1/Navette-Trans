package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.model.Employeur;
import com.platformtrasnport.platformtransport.service.Impl.EmployeurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employeurs")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeurController {

    @Autowired
    private EmployeurServiceImpl employeurService;

    @GetMapping
    public List<Employeur> getAllEmployeurs() {
        return employeurService.getAllEmployeurs();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<EmployeurDto> getEmployeurById(@PathVariable("id") Long id) {
//        return employeurService.getEmployeurById(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<EmployeurDto> createEmployeur(@RequestBody EmployeurDto employeurDto) {
//        EmployeurDto createdEmployeur = employeurService.createEmployeur(employeurDto);
//        return new ResponseEntity<>(createdEmployeur, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<EmployeurDto> updateEmployeur(@PathVariable("id") Long id, @RequestBody EmployeurDto employeurDto) {
//        return employeurService.updateEmployeur(id, employeurDto)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteEmployeur(@PathVariable("id") Long id) {
//        if (employeurService.deleteEmployeur(id)) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
