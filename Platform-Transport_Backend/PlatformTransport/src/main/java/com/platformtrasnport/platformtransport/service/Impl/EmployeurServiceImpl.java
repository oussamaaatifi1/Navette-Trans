package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.dto.EmployeurDto;
import com.platformtrasnport.platformtransport.mapper.EmployeurMapper;
import com.platformtrasnport.platformtransport.model.Employeur;
import com.platformtrasnport.platformtransport.repository.EmployeurRepository;
import com.platformtrasnport.platformtransport.service.EmployeurService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeurServiceImpl implements EmployeurService {

    private final EmployeurRepository employeurRepository;
    private final EmployeurMapper employeurMapper;

    // Constructor-based injection
    public EmployeurServiceImpl(EmployeurRepository employeurRepository, EmployeurMapper employeurMapper) {
        this.employeurRepository = employeurRepository;
        this.employeurMapper = employeurMapper;
    }

    @Override
    public List<EmployeurDto> getAllEmployeurs() {
        return employeurRepository.findAll().stream()
                .map(employeurMapper::employeurToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeurDto createEmployeur(EmployeurDto employeurDto) {
        Employeur employeur = employeurMapper.dtoToEmployeur(employeurDto);
        Employeur createdEmployeur = employeurRepository.save(employeur);
        return employeurMapper.employeurToDto(createdEmployeur);
    }

    @Override
    public Optional<EmployeurDto> updateEmployeur(Long id, EmployeurDto employeurDto) {
        return employeurRepository.findById(id).map(existingEmployeur -> {
            Employeur updatedEmployeur = employeurMapper.dtoToEmployeur(employeurDto);
            updatedEmployeur.setId(id); // Ensure ID is set
            employeurRepository.save(updatedEmployeur);
            return employeurMapper.employeurToDto(updatedEmployeur);
        });
    }

    @Override
    public boolean deleteEmployeur(Long id) {
        if (employeurRepository.existsById(id)) {
            employeurRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
