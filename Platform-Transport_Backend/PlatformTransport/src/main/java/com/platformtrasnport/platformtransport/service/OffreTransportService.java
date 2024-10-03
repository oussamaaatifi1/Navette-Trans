package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.dto.OffreTransportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OffreTransportService {

    OffreTransportDto createOffreTransport(OffreTransportDto offreTransportDto, String token); // Updated method

    Optional<OffreTransportDto> findById(Long id);

    OffreTransportDto rejectOffre(Long id);

    OffreTransportDto approveOffre(Long id);

    List<OffreTransportDto> findOffreTransportByEmployeurId(Long employeurId);

//    List<OffreTransportDto> findPendingOffres();
public List<OffreTransportDto> findPendingOffres();
    List<OffreTransportDto> findApprovedOffres();
    OffreTransportDto findApprovedOffreById(Long id);


    void deleteOffreTransport(Long id);

    List<OffreTransportDto> findAllOffres();

    List<OffreTransportDto> findRejectedOffres();

    OffreTransportDto updateOffreTransport(Long id, OffreTransportDto offreTransportDto);
}
