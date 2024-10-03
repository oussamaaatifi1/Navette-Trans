package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.dto.OffreTransportDto;
import com.platformtrasnport.platformtransport.model.Employeur;
import com.platformtrasnport.platformtransport.service.JwtService;
import com.platformtrasnport.platformtransport.service.OffreTransportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/offres-transport")
public class OffreTransportController {

    private final OffreTransportService offreTransportService;

    public OffreTransportController(OffreTransportService offreTransportService) {
        this.offreTransportService = offreTransportService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEUR', 'ADMIN')")
    public ResponseEntity<OffreTransportDto> getOffreTransportById(@PathVariable Long id) {
        OffreTransportDto offreTransportDto = offreTransportService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offre Transport non trouvée avec l'id " + id));
        return ResponseEntity.ok(offreTransportDto);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('EMPLOYEUR', 'ADMIN')")
    public ResponseEntity<List<OffreTransportDto>> getAllOffresTransports() {
        List<OffreTransportDto> offres = offreTransportService.findAllOffres();
        return ResponseEntity.ok(offres);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('EMPLOYEUR')")
    public ResponseEntity<OffreTransportDto> createOffreTransport(
            @RequestBody OffreTransportDto offreTransportDto,
            @RequestHeader("Authorization") String token) {

        OffreTransportDto createdOffre = offreTransportService.createOffreTransport(offreTransportDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOffre);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEUR', 'ADMIN')")
    public ResponseEntity<OffreTransportDto> updateOffreTransport(@PathVariable Long id, @RequestBody OffreTransportDto offreTransportDto) {
        OffreTransportDto updatedOffreTransportDto = offreTransportService.updateOffreTransport(id, offreTransportDto);
        return ResponseEntity.ok(updatedOffreTransportDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEUR')")
    public ResponseEntity<Map<String, Boolean>> deleteOffreTransport(@PathVariable Long id) {
        offreTransportService.deleteOffreTransport(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/approved")
    @PreAuthorize("hasAnyRole('EMPLOYEUR', 'EMPLOYE')")
    public ResponseEntity<List<OffreTransportDto>> getApprovedOffreTransports() {
        List<OffreTransportDto> approvedOffres = offreTransportService.findApprovedOffres();
        return ResponseEntity.ok(approvedOffres);
    }

    @GetMapping("/approved/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEUR', 'EMPLOYE')")
    public ResponseEntity<OffreTransportDto> getApprovedOffreById(@PathVariable Long id) {
        OffreTransportDto approvedOffre = offreTransportService.findApprovedOffreById(id);
        return ResponseEntity.ok(approvedOffre);
    }

    @GetMapping("/offreByEmployeur")
    @PreAuthorize("hasRole('EMPLOYEUR')")
    public ResponseEntity<List<OffreTransportDto>> getOffreTransportByEmployeur(Authentication authentication) {
        Long userId = ((Employeur) authentication.getPrincipal()).getId();
        List<OffreTransportDto> offres = offreTransportService.findOffreTransportByEmployeurId(userId);
        return ResponseEntity.ok(offres);
    }

    @GetMapping("/rejected")
    @PreAuthorize("hasAnyRole('EMPLOYEUR', 'EMPLOYE')")
    public ResponseEntity<List<OffreTransportDto>> getRejectedOffreTransports() {
        List<OffreTransportDto> rejectedOffres = offreTransportService.findRejectedOffres();
        return ResponseEntity.ok(rejectedOffres);
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OffreTransportDto> approveOffre(@PathVariable Long id) {
        OffreTransportDto approvedOffreTransportDto = offreTransportService.approveOffre(id);
        return ResponseEntity.ok(approvedOffreTransportDto);
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OffreTransportDto> rejectOffre(@PathVariable Long id) {
        OffreTransportDto rejectedOffreTransportDto = offreTransportService.rejectOffre(id);
        return ResponseEntity.ok(rejectedOffreTransportDto);
    }
}
