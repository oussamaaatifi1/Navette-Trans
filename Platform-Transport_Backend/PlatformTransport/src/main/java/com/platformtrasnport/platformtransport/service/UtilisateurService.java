package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.dto.EmployeDto;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    List<EmployeDto> getAllUtilisateurs();
    EmployeDto createUtilisateur(EmployeDto employe);
    Optional<EmployeDto> getUtilisateurById(Long id);
    Optional<EmployeDto> updateUtilisateur(Long id, EmployeDto employe);
    boolean deleteUtilisateur(Long id);

}
