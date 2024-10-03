package com.platformtrasnport.platformtransport.service.impl;

import com.platformtrasnport.platformtransport.dto.OffreTransportDto;
import com.platformtrasnport.platformtransport.mapper.OffreTransportMapper;
import com.platformtrasnport.platformtransport.model.Employeur;
import com.platformtrasnport.platformtransport.model.OffreTransport;
import com.platformtrasnport.platformtransport.model.enul.OffreStatus;
import com.platformtrasnport.platformtransport.repository.OffreTransportRepository;
import com.platformtrasnport.platformtransport.repository.EmployeurRepository;
import com.platformtrasnport.platformtransport.service.JwtService;
import com.platformtrasnport.platformtransport.service.OffreTransportService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OffreTransportServiceImpl implements OffreTransportService {

    private final OffreTransportRepository offreTransportRepository;
    private final OffreTransportMapper offreTransportMapper;
    private final EmployeurRepository employeurRepository;
    private final JwtService jwtService;

    public OffreTransportServiceImpl(OffreTransportRepository offreTransportRepository,
                                     OffreTransportMapper offreTransportMapper,
                                     EmployeurRepository employeurRepository,
                                     JwtService jwtService) {
        this.offreTransportRepository = offreTransportRepository;
        this.offreTransportMapper = offreTransportMapper;
        this.employeurRepository = employeurRepository;
        this.jwtService = jwtService;
    }

    @Override
    public OffreTransportDto createOffreTransport(OffreTransportDto offreTransportDto, String token) {
        Long userId = jwtService.extractUserId(token.substring(7));
        offreTransportDto.setEmployeurId(userId);

        // Fetch Employeur entity by employeurId
        Employeur employeur = employeurRepository.findById(offreTransportDto.getEmployeurId())
                .orElseThrow(() -> new EntityNotFoundException("Employeur not found with id: " + offreTransportDto.getEmployeurId()));

        OffreTransport offreTransport = offreTransportMapper.dtoToOffreTransport(offreTransportDto);
        offreTransport.setDateOffre(LocalDate.now());
        offreTransport.setStatus(OffreStatus.PENDING);
        offreTransport.setEmployeur(employeur);

        OffreTransport savedOffre = offreTransportRepository.save(offreTransport);
        return offreTransportMapper.offreTransportToDto(savedOffre);
    }

    @Override
    public List<OffreTransportDto> findPendingOffres() {
        return offreTransportRepository.findByStatus(OffreStatus.PENDING).stream()
                .map(offreTransportMapper::offreTransportToDto)
                .toList(); // Changed to Stream.toList()
    }

    @Override
    public Optional<OffreTransportDto> findById(Long id) {
        return offreTransportRepository.findById(id)
                .map(offreTransportMapper::offreTransportToDto);
    }

    @Override
    public List<OffreTransportDto> findAllOffres() {
        return offreTransportRepository.findAll().stream()
                .map(offreTransportMapper::offreTransportToDto)
                .toList(); // Changed to Stream.toList()
    }

    @Override
    public List<OffreTransportDto> findApprovedOffres() {
        return offreTransportRepository.findByStatus(OffreStatus.APPROVED).stream()
                .map(offreTransportMapper::offreTransportToDto)
                .toList(); // Changed to Stream.toList()
    }

    @Override
    public OffreTransportDto findApprovedOffreById(Long id) {
        return offreTransportRepository.findByIdAndStatus(id, OffreStatus.APPROVED)
                .map(offreTransportMapper::offreTransportToDto)
                .orElseThrow(() -> new ResourceNotFoundException("OffreTransport not found with id " + id));
    }

    @Override
    public List<OffreTransportDto> findRejectedOffres() {
        return offreTransportRepository.findByStatus(OffreStatus.REJECTED).stream()
                .map(offreTransportMapper::offreTransportToDto)
                .toList(); // Changed to Stream.toList()
    }

    @Override
    public OffreTransportDto approveOffre(Long id) {
        OffreTransport offreTransport = offreTransportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offre not found"));
        offreTransport.setStatus(OffreStatus.APPROVED);
        OffreTransport updatedOffre = offreTransportRepository.save(offreTransport);
        return offreTransportMapper.offreTransportToDto(updatedOffre);
    }

    @Override
    public List<OffreTransportDto> findOffreTransportByEmployeurId(Long employeurId) {
        List<OffreTransport> offers = offreTransportRepository.findOffreTransportsByEmployeurId(employeurId);
        return offers.stream()
                .map(offreTransportMapper::offreTransportToDto)
                .toList(); // Changed to Stream.toList()
    }

    @Override
    public OffreTransportDto rejectOffre(Long id) {
        OffreTransport offreTransport = offreTransportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offre not found"));
        offreTransport.setStatus(OffreStatus.REJECTED);
        OffreTransport updatedOffre = offreTransportRepository.save(offreTransport);
        return offreTransportMapper.offreTransportToDto(updatedOffre);
    }

    @Override
    public OffreTransportDto updateOffreTransport(Long id, OffreTransportDto offreTransportDto) {
        OffreTransport offreTransport = offreTransportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OffreTransport not found"));

        // Update fields
        offreTransport.setNombrePlaces(offreTransportDto.getNombrePlaces());
        offreTransport.setDateOffre(offreTransportDto.getDateOffre());
        offreTransport.setPrix(offreTransportDto.getPrix());
        offreTransport.setStatus(offreTransportDto.getStatus());

        OffreTransport updatedOffre = offreTransportRepository.save(offreTransport);
        return offreTransportMapper.offreTransportToDto(updatedOffre);
    }

    @Override
    public void deleteOffreTransport(Long id) {
        if (!offreTransportRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OffreTransport not found");
        }
        offreTransportRepository.deleteById(id);
    }
}
