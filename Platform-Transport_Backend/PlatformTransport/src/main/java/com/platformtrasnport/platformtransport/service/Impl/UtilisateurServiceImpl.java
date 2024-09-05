package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.model.Employe;
import com.platformtrasnport.platformtransport.model.enul.Role;
import com.platformtrasnport.platformtransport.repository.EmployeRepository;
import com.platformtrasnport.platformtransport.repository.UtilisateurRepository;
import com.platformtrasnport.platformtransport.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public List<Employe> getAllUtilisateurs() {
        return employeRepository.findAll();
    }

    @Override
    public Employe createUtilisateur(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public Optional<Employe> getUtilisateurById(Long id) {
        return employeRepository.findById(id);
    }

    @Override
    public Optional<Employe> updateUtilisateur(Long id, Employe employe) {
        if (employeRepository.existsById(id)) {
            employe.setId(id);
            return Optional.of(employeRepository.save(employe));
        } else {
            return Optional.empty();
        }
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
