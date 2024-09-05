package com.platformtrasnport.platformtransport.repository;

import com.platformtrasnport.platformtransport.model.Particulier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ParticulierRepository extends JpaRepository<Particulier, Long> {
}