package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.model.OffreTransport;
import com.platformtrasnport.platformtransport.repository.OffreTransportRepository;
import com.platformtrasnport.platformtransport.service.OffreTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/offres-transport")
@CrossOrigin(origins = "http://localhost:4200/")
public class OffreTransportController {

    @Autowired
    private OffreTransportService offreTransportService;

    @Autowired
    private OffreTransportRepository offreTransportRepository;


    @GetMapping("/all")
    public List<OffreTransport> getallOffreTransports() {
        return offreTransportRepository.findAll();
    }

    @PostMapping("/add")
    public OffreTransport createOffreTransport(@RequestBody OffreTransport offreTransport) {
        return offreTransportService.createOffreTransport(offreTransport);
    }


    @GetMapping("/OffreTransport/{id}")
    public ResponseEntity<OffreTransport> editOffreTransport(@PathVariable long id) {
            OffreTransport offreTransport = offreTransportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Offre Transport Not Found id " + id));
        return ResponseEntity.ok(offreTransport);
    }

    @PutMapping("/OffreTransport/{id}")
    public ResponseEntity<OffreTransport> updateOffreTransport(@PathVariable long id ,@RequestBody OffreTransport offreTransport){
        OffreTransport offreTransports = offreTransportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Offre Transport Not Found id " + id));
        offreTransports.setPointDepart(offreTransport.getPointDepart());
        offreTransports.setDestination(offreTransport.getDestination());
        offreTransports.setDateOffre(offreTransport.getDateOffre());
        offreTransports.setNombrePlaces(offreTransport.getNombrePlaces());
        offreTransports.setTypeOffreTransport(offreTransport.getTypeOffreTransport());
        offreTransports.setPrix(offreTransport.getPrix());

        OffreTransport offreTransportup  = offreTransportRepository.save(offreTransport);
        return ResponseEntity.ok(offreTransportup);
    }

    @DeleteMapping("/OffreTransport/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteOffreTransport(@PathVariable long id){
        OffreTransport offreTransport = offreTransportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Delete Offre Transport is id " + id));
        offreTransportRepository.delete(offreTransport);
        Map<String,Boolean> response= new HashMap<>();
        response.put("delete",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
