package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.dto.RapportDto;
import com.platformtrasnport.platformtransport.mapper.RapportMapper;
import com.platformtrasnport.platformtransport.model.Rapport;
import com.platformtrasnport.platformtransport.repository.RapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RapportServiceImpl {

    private final RapportRepository rapportRepository;

    public RapportServiceImpl(RapportRepository rapportRepository) {
        this.rapportRepository = rapportRepository;
    }

    // Fetch all rapports and convert to RapportDto
    public List<RapportDto> getAllRapports() {
        return rapportRepository.findAll().stream()
                .map(RapportMapper.INSTANCE::rapportToDto)
                .collect(Collectors.toList());
    }

    // Save a rapport and return RapportDto
    public RapportDto saveRapport(Rapport rapport) {
        Rapport savedRapport = rapportRepository.save(rapport);
        return RapportMapper.INSTANCE.rapportToDto(savedRapport);
    }
}
