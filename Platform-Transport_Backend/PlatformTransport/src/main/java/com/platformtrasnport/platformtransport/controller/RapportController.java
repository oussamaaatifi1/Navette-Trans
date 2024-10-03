package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.dto.RapportDto;
import com.platformtrasnport.platformtransport.mapper.RapportMapper;
import com.platformtrasnport.platformtransport.model.Administrateur;
import com.platformtrasnport.platformtransport.model.Rapport;
import com.platformtrasnport.platformtransport.service.impl.AdministrateurServiceImpl;
import com.platformtrasnport.platformtransport.service.impl.RapportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rapports")
@CrossOrigin(origins = "http://localhost:4200")
public class RapportController {

    private final RapportServiceImpl rapportService;
    private final AdministrateurServiceImpl administrateurService;

    @Autowired
    public RapportController(RapportServiceImpl rapportService, AdministrateurServiceImpl administrateurService) {
        this.rapportService = rapportService;
        this.administrateurService = administrateurService;
    }

    // Fetch all rapports
    @GetMapping
    public List<RapportDto> getAllRapports() {
        return rapportService.getAllRapports();
    }

    // Create new rapport
    @PostMapping
    public ResponseEntity<RapportDto> createRapport(@RequestBody RapportDto rapportDto) {
        Rapport rapport = RapportMapper.INSTANCE.dtoToRapport(rapportDto);

        // Check if the administrateurId is set, then fetch or save the administrateur
        if (rapportDto.getAdministrateurId() != null) {
            Administrateur admin = administrateurService.findById(rapportDto.getAdministrateurId());
            if (admin == null) {
                throw new RuntimeException("Administrateur not found with ID: " + rapportDto.getAdministrateurId());
            }
            rapport.setAdministrateur(admin);
        } else {
            // Handle case where administrateurId is not provided
            throw new RuntimeException("Administrateur ID must be provided.");
        }

        RapportDto savedRapportDto = rapportService.saveRapport(rapport);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRapportDto);
    }

}
