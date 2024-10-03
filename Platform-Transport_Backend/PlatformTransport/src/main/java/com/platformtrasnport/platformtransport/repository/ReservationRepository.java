package com.platformtrasnport.platformtransport.repository;

import com.platformtrasnport.platformtransport.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.employe.id = :employeId")
    List<Reservation> findReservationsByEmployeId(Long employeId);

    @Query("SELECT COUNT(r) FROM Reservation r")
    Long countTotalReservations();

    @Query("SELECT r.employe.id, SUM(r.transaction.montant) FROM Reservation r GROUP BY r.employe.id")
    List<Object[]> sumMontantByEmploye();

}