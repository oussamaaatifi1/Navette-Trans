package com.platformtrasnport.platformtransport.repository;

import com.platformtrasnport.platformtransport.model.Employeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeurRepository extends JpaRepository<Employeur, Long> {

    Optional<Employeur> findById(Long id);
}
