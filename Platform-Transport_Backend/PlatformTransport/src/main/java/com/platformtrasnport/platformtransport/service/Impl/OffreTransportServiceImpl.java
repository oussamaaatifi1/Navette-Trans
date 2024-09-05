package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.model.OffreTransport;
import com.platformtrasnport.platformtransport.repository.OffreTransportRepository;
import com.platformtrasnport.platformtransport.service.OffreTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OffreTransportServiceImpl implements OffreTransportService {

    @Autowired
    private OffreTransportRepository offreTransportRepository;

    @Override
    public OffreTransport createOffreTransport(OffreTransport offreTransport) {
        offreTransport.setDateOffre(LocalDate.now());
        return offreTransportRepository.save(offreTransport);
    }


    @Override
    public Optional<OffreTransport> findById(Long id) {
        return offreTransportRepository.findById(id);
    }

}
