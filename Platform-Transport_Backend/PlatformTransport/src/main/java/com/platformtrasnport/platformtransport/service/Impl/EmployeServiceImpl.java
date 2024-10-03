package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.dto.EmployeDto;
import com.platformtrasnport.platformtransport.mapper.EmployeMapper;
import com.platformtrasnport.platformtransport.model.Employe;
import com.platformtrasnport.platformtransport.repository.EmployeRepository;
import com.platformtrasnport.platformtransport.service.EmployeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository employeRepository;
    private final EmployeMapper employeMapper;

    // Constructor-based injection
    public EmployeServiceImpl(EmployeRepository employeRepository, EmployeMapper employeMapper) {
        this.employeRepository = employeRepository;
        this.employeMapper = employeMapper;
    }

    @Override
    public List<EmployeDto> getAllEmployes() {
        return employeRepository.findAll().stream()
                .map(employeMapper::employeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeDto createEmploye(EmployeDto employeDto) {
        Employe employe = employeMapper.dtoToEmploye(employeDto);
        Employe createdEmploye = employeRepository.save(employe);
        return employeMapper.employeToDto(createdEmploye);
    }

    @Override
    public Optional<EmployeDto> getEmployeById(Long id) {
        return employeRepository.findById(id)
                .map(employeMapper::employeToDto);
    }

    @Override
    public Optional<EmployeDto> updateUtilisateur(Long id, EmployeDto employeDto) {
        Optional<Employe> existingEmployeOpt = employeRepository.findById(id);
        if (existingEmployeOpt.isPresent()) {
            Employe existingEmploye = existingEmployeOpt.get();
            existingEmploye.setPrenom(employeDto.getPrenom());
            existingEmploye.setNom(employeDto.getNom());
            existingEmploye.setEmail(employeDto.getEmail());
            existingEmploye.setPhone(employeDto.getPhone());
            existingEmploye.setAddress(employeDto.getAddress());
            existingEmploye.setDateOfBirth(employeDto.getDateOfBirth());
            existingEmploye.setImgUrl(employeDto.getImgUrl());

            Employe updatedEmploye = employeRepository.save(existingEmploye);
            return Optional.of(employeMapper.employeToDto(updatedEmploye));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteUtilisateur(Long id) {
        if (employeRepository.existsById(id)) {
            employeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
