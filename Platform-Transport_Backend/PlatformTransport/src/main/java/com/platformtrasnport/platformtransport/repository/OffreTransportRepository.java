package com.platformtrasnport.platformtransport.repository;

import com.platformtrasnport.platformtransport.dto.EmployeurDto;
import com.platformtrasnport.platformtransport.dto.OffreTransportDto;
import com.platformtrasnport.platformtransport.model.OffreTransport;
import com.platformtrasnport.platformtransport.model.enul.OffreStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffreTransportRepository extends JpaRepository<OffreTransport, Long> {
    List<OffreTransport> findByPointDepart(String pointDepart);
    List<OffreTransport> findByStatus(OffreStatus status);

    List<OffreTransport> findOffreTransportsByEmployeurId(Long employeurId);



//    @Query("select * from OffreTransport where employeur_id = ")
//    List<EmployeurDto>getallOffreByemployeur();
}