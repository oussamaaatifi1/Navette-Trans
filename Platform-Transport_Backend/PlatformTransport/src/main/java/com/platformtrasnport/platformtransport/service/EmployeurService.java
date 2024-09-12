package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.dto.EmployeurDto;

import java.util.List;
import java.util.Optional;

public interface EmployeurService {
    List<EmployeurDto> getAllEmployeurs();
    Optional<EmployeurDto> updateEmployeur(Long id, EmployeurDto employeurDto);
    boolean deleteEmployeur(Long id);
}