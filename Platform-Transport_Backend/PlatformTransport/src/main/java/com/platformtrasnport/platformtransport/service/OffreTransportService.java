package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.dto.OffreTransportDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OffreTransportService {

    OffreTransportDto createOffreTransport(OffreTransportDto offreTransportDto);

    Optional<OffreTransportDto> findById(Long id);

    OffreTransportDto rejectOffre(Long id);

    OffreTransportDto approveOffre(Long id);

    List<OffreTransportDto> findPendingOffres();

    List<OffreTransportDto> findApprovedOffres();

    void deleteOffreTransport(Long id);

    List<OffreTransportDto> findAllOffres();

    List<OffreTransportDto> findRejectedOffres();

    OffreTransportDto updateOffreTransport(Long id, OffreTransportDto offreTransportDto);
}