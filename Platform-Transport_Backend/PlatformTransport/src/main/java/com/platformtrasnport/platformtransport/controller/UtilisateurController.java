package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.model.Employe;
import com.platformtrasnport.platformtransport.service.Impl.UtilisateurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {

    @Autowired
    private UtilisateurServiceImpl utilisateurService;

    @GetMapping
    public List<Employe> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @PostMapping
    public ResponseEntity<Employe> createUtilisateur(@RequestBody Employe employe) {
        Employe createdEmploye = utilisateurService.createUtilisateur(employe);
        return new ResponseEntity<>(createdEmploye, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getUtilisateurById(@PathVariable("id") Long id) {
        Optional<Employe> employe = utilisateurService.getUtilisateurById(id);
        return employe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> updateUtilisateur(@PathVariable("id") Long id, @RequestBody Employe employe) {
        Optional<Employe> updatedEmploye = utilisateurService.updateUtilisateur(id, employe);
        return updatedEmploye.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable("id") Long id) {
        if (utilisateurService.deleteUtilisateur(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
