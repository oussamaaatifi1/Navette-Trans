package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.model.Employe;
import com.platformtrasnport.platformtransport.repository.EmployeRepository;
import com.platformtrasnport.platformtransport.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EmployeServiceImpl implements EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public Optional<Employe> findById(Long id) {
        return employeRepository.findById(id);
    }
}
