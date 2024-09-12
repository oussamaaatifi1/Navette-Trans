package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.dto.OffreTransportDto;
import com.platformtrasnport.platformtransport.mapper.OffreTransportMapper;
import com.platformtrasnport.platformtransport.model.OffreTransport;
import com.platformtrasnport.platformtransport.model.enul.OffreStatus;
import com.platformtrasnport.platformtransport.repository.OffreTransportRepository;
import com.platformtrasnport.platformtransport.service.OffreTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OffreTransportServiceImpl implements OffreTransportService {

    @Autowired
    private  OffreTransportRepository offreTransportRepository;

    @Autowired
    private OffreTransportMapper offreTransportMapper;


    @Override
    public OffreTransportDto createOffreTransport(OffreTransportDto offreTransportDto) {
        OffreTransport offreTransport = offreTransportMapper.dtoToOffreTransport(offreTransportDto);
        offreTransport.setDateOffre(LocalDate.now());
        offreTransport.setStatus(OffreStatus.PENDING);
        OffreTransport savedOffre = offreTransportRepository.save(offreTransport);
        return offreTransportMapper.offreTransportToDto(savedOffre);
    }

    @Override
    public Optional<OffreTransportDto> findById(Long id) {
        return offreTransportRepository.findById(id)
                .map(offreTransportMapper::offreTransportToDto);
    }

    @Override
    public List<OffreTransportDto> findPendingOffres() {
        return offreTransportRepository.findByStatus(OffreStatus.PENDING).stream()
                .map(offreTransportMapper::offreTransportToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OffreTransportDto> findAllOffres() {
        return offreTransportRepository.findAll().stream()
                .map(offreTransportMapper::offreTransportToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OffreTransportDto> findApprovedOffres() {
        return offreTransportRepository.findByStatus(OffreStatus.APPROVED).stream()
                .map(offreTransportMapper::offreTransportToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OffreTransportDto> findRejectedOffres() {
        return offreTransportRepository.findByStatus(OffreStatus.REJECTED).stream()
                .map(offreTransportMapper::offreTransportToDto)
                .collect(Collectors.toList());
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
    public OffreTransportDto rejectOffre(Long id) {
        OffreTransport offreTransport = offreTransportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offre not found"));
        offreTransport.setStatus(OffreStatus.REJECTED);
        OffreTransport updatedOffre = offreTransportRepository.save(offreTransport);
        return offreTransportMapper.offreTransportToDto(updatedOffre);
    }

    @Override
    public void deleteOffreTransport(Long id) {
        if (!offreTransportRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offre not found");
        }
        offreTransportRepository.deleteById(id);
    }

    @Override
    public OffreTransportDto updateOffreTransport(Long id, OffreTransportDto offreTransportDto) {
        OffreTransport existingOffreTransport = offreTransportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offre not found"));

        OffreTransport updatedOffreTransport = offreTransportMapper.dtoToOffreTransport(offreTransportDto);
        updatedOffreTransport.setId(id);
        updatedOffreTransport.setStatus(existingOffreTransport.getStatus());  // Preserve the existing status

        OffreTransport savedOffre = offreTransportRepository.save(updatedOffreTransport);
        return offreTransportMapper.offreTransportToDto(savedOffre);
    }
}