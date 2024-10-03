package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.dto.EmployeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeService {

    List<EmployeDto> getAllEmployes();

    EmployeDto createEmploye(EmployeDto employeDto);

    boolean deleteUtilisateur(Long id);

    Optional<EmployeDto> updateUtilisateur(Long id, EmployeDto employeDto);

    Optional<EmployeDto> getEmployeById(Long id);

}
