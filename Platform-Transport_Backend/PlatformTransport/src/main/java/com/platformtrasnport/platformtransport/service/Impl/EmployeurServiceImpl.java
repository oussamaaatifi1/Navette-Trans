package com.platformtrasnport.platformtransport.service.Impl;

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

//    @Autowired
//    private EmployeurMapper employeurMapper;

    @Override
    public List<Employeur> getAllEmployeurs() {
        return employeurRepository.findAll();

    }
    }
//    @Override
//    public EmployeurDto createEmployeur(EmployeurDto employeurDto) {
//        Employeur employeur = employeurMapper.dtoToEmployeur(employeurDto);
//        Employeur createdEmployeur = employeurRepository.save(employeur);
//        return employeurMapper.employeurToDto(createdEmployeur);
//    }
//
//    @Override
//    public Optional<EmployeurDto> getEmployeurById(Long id) {
//        return employeurRepository.findById(id)
//                .map(employeurMapper::employeurToDto);
//    }
//
//    @Override
//    public Optional<EmployeurDto> updateEmployeur(Long id, EmployeurDto employeurDto) {
//        return employeurRepository.findById(id).map(existingEmployeur -> {
//            Employeur updatedEmployeur = employeurMapper.dtoToEmployeur(employeurDto);
//            updatedEmployeur.setId(id);
//            employeurRepository.save(updatedEmployeur);
//            return employeurMapper.employeurToDto(updatedEmployeur);
//        });
//    }
//
//    @Override
//    public boolean deleteEmployeur(Long id) {
//        if (employeurRepository.existsById(id)) {
//            employeurRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }

