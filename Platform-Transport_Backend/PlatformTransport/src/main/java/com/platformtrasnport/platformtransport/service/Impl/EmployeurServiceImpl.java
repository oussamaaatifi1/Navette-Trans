package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.dto.EmployeurDto;
import com.platformtrasnport.platformtransport.mapper.EmployeurMapper;
import com.platformtrasnport.platformtransport.model.Employeur;
import com.platformtrasnport.platformtransport.repository.EmployeurRepository;
import com.platformtrasnport.platformtransport.service.EmployeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeurServiceImpl implements EmployeurService {
    @Autowired
    private EmployeurRepository employeurRepository;

    @Autowired
    private EmployeurMapper employeurMapper;

    @Override
    public List<EmployeurDto> getAllEmployeurs() {
        return employeurRepository.findAll().stream()
                .map(employeurMapper::employeurToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeurDto> updateEmployeur(Long id, EmployeurDto employeurDto) {
        if (employeurRepository.existsById(id)) {
            Employeur employeur = employeurMapper.dtoToEmployeur(employeurDto);
            employeur.setId(id);
            Employeur updatedEmployeur = employeurRepository.save(employeur);
            return Optional.of(employeurMapper.employeurToDto(updatedEmployeur));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteEmployeur(Long id) {
        if (employeurRepository.existsById(id)) {
            employeurRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}