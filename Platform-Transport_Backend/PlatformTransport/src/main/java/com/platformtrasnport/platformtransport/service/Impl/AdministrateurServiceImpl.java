package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.model.Administrateur;
import com.platformtrasnport.platformtransport.repository.AdministrateurRepository;
import com.platformtrasnport.platformtransport.service.AdministrateurService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdministrateurServiceImpl implements AdministrateurService {

    private final AdministrateurRepository administrateurRepository;

    // Constructor-based injection
    public AdministrateurServiceImpl(AdministrateurRepository administrateurRepository) {
        this.administrateurRepository = administrateurRepository;
    }

    @Override
    public long getAdministrateurCount() {
        return administrateurRepository.countAdministrateurs();
    }

    public Administrateur findById(Long id) {
        return administrateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrateur not found with id " + id));
    }
}
