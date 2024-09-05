package com.platformtrasnport.platformtransport.repository;

import com.platformtrasnport.platformtransport.model.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RapportRepository extends JpaRepository<Rapport, Long> {
}