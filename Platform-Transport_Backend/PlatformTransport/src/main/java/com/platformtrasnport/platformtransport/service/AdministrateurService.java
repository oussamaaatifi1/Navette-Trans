package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.model.Administrateur;

public interface AdministrateurService {
    long getAdministrateurCount();
    Administrateur findById(Long id);
}
