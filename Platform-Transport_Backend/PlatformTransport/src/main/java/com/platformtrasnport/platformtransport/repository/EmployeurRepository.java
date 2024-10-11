package com.platformtrasnport.platformtransport.repository;

import com.platformtrasnport.platformtransport.dto.ReservationDto;
import com.platformtrasnport.platformtransport.model.Employeur;
import com.platformtrasnport.platformtransport.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeurRepository extends JpaRepository<Employeur, Long> {

    Optional<Employeur> findById(Long id);
}
