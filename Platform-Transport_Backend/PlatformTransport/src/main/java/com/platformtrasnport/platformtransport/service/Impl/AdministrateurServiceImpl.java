package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.repository.AdministrateurRepository;
import com.platformtrasnport.platformtransport.service.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrateurServiceImpl implements AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;
    @Override
    public long getAdministrateurCount() {
        return administrateurRepository.countAdministrateurs();
    }
}
