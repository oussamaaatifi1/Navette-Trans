package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.dto.EmployeurDto;
import com.platformtrasnport.platformtransport.model.Employeur;

import java.util.List;
import java.util.Optional;

public interface EmployeurService {

    List<Employeur> getAllEmployeurs();

//    EmployeurDto createEmployeur(EmployeurDto employeurDto);  // Create a new employeur with a DTO
//    Optional<EmployeurDto> getEmployeurById(Long id);  // Get an employeur by ID as a DTO
//    Optional<EmployeurDto> updateEmployeur(Long id, EmployeurDto employeurDto);  // Update employeur using a DTO
//    boolean deleteEmployeur(Long id);  // Delete an employeur by ID
}
