package com.platformtrasnport.platformtransport.service;


import com.platformtrasnport.platformtransport.model.Employe;
import java.util.Optional;


public interface EmployeService {

    Optional<Employe> findById(Long id);

}
