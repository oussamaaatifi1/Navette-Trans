package com.platformtrasnport.platformtransport.repository;

import com.platformtrasnport.platformtransport.model.OffreTransport;
import com.platformtrasnport.platformtransport.model.enul.OffreStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffreTransportRepository extends JpaRepository<OffreTransport, Long> {
    List<OffreTransport> findByPointDepart(String pointDepart);
    List<OffreTransport> findByStatus(OffreStatus status);

}