package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.dto.OffreTransportDto;
import com.platformtrasnport.platformtransport.service.OffreTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/offres-transport")
public class OffreTransportController {

    @Autowired
    private OffreTransportService offreTransportService;

    @GetMapping("/all")
    public List<OffreTransportDto> getAllOffreTransports() {
        return offreTransportService.findPendingOffres();
    }

    @PostMapping("/add")
    public OffreTransportDto createOffreTransport(@RequestBody OffreTransportDto offreTransportDto) {
        return offreTransportService.createOffreTransport(offreTransportDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OffreTransportDto> getOffreTransport(@PathVariable Long id) {
        OffreTransportDto offreTransportDto = offreTransportService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offre Transport Not Found id " + id));
        return ResponseEntity.ok(offreTransportDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OffreTransportDto> updateOffreTransport(@PathVariable Long id, @RequestBody OffreTransportDto offreTransportDto) {
        OffreTransportDto updatedOffreTransportDto = offreTransportService.updateOffreTransport(id, offreTransportDto);
        return ResponseEntity.ok(updatedOffreTransportDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOffreTransport(@PathVariable Long id) {
        offreTransportService.deleteOffreTransport(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/approved")
    public List<OffreTransportDto> getApprovedOffreTransports() {
        return offreTransportService.findApprovedOffres();
    }

    @GetMapping("/rejected")
    public List<OffreTransportDto> getRejectedOffreTransports() {
        return offreTransportService.findRejectedOffres();
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<OffreTransportDto> approveOffre(@PathVariable Long id) {
        OffreTransportDto approvedOffreTransportDto = offreTransportService.approveOffre(id);
        return ResponseEntity.ok(approvedOffreTransportDto);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<OffreTransportDto> rejectOffre(@PathVariable Long id) {
        OffreTransportDto rejectedOffreTransportDto = offreTransportService.rejectOffre(id);
        return ResponseEntity.ok(rejectedOffreTransportDto);
    }
}