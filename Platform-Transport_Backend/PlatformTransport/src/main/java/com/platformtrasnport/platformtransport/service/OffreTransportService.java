package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.model.OffreTransport;
import com.platformtrasnport.platformtransport.repository.OffreTransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface OffreTransportService {

    public OffreTransport createOffreTransport(OffreTransport offreTransport);

    public Optional<OffreTransport> findById(Long id);

}
