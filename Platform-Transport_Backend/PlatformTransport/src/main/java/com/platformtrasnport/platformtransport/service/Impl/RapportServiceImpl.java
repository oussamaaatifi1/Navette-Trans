package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.dto.RapportDto;
import com.platformtrasnport.platformtransport.mapper.RapportMapper;
import com.platformtrasnport.platformtransport.model.Rapport;
import com.platformtrasnport.platformtransport.repository.RapportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RapportServiceImpl {

    private final RapportRepository rapportRepository;

    public RapportServiceImpl(RapportRepository rapportRepository) {
        this.rapportRepository = rapportRepository;
    }

    public List<RapportDto> getAllRapports() {
        return rapportRepository.findAll().stream()
                .map(RapportMapper.INSTANCE::rapportToDto)
                .toList(); // Changed from collect(Collectors.toList()) to toList()
    }

    public RapportDto saveRapport(Rapport rapport) {
        Rapport savedRapport = rapportRepository.save(rapport);
        return RapportMapper.INSTANCE.rapportToDto(savedRapport);
    }
}
