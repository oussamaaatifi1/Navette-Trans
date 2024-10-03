package com.platformtrasnport.platformtransport.repository;

import com.platformtrasnport.platformtransport.model.Employe;
import com.platformtrasnport.platformtransport.model.Employeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    Employe findEmployeById(Long id);
}