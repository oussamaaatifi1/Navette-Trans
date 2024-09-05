package com.platformtrasnport.platformtransport.repository;

import com.platformtrasnport.platformtransport.model.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
    @Query("SELECT COUNT(u) FROM Employe u WHERE u.role = 'EMPLOYE'")
    long countAdministrateurs();
}