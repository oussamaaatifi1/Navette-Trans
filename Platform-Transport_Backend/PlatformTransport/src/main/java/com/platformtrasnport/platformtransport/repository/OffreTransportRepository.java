package com.platformtrasnport.platformtransport.repository;

import com.platformtrasnport.platformtransport.model.OffreTransport;
import com.platformtrasnport.platformtransport.model.Reservation;
import com.platformtrasnport.platformtransport.model.enul.OffreStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OffreTransportRepository extends JpaRepository<OffreTransport, Long> {
    List<OffreTransport> findByStatus(OffreStatus status);
    List<OffreTransport> findOffreTransportsByEmployeurId(Long employeurId);
    Optional<OffreTransport> findByIdAndStatus(Long id, OffreStatus status);

    @Query("SELECT r FROM Reservation r WHERE r.employe.id = :employeId")
    List<Reservation> findReservationsByEmployeId(Long employeId);
}
