package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.dto.ReservationDto;
import com.platformtrasnport.platformtransport.model.Employe;

import java.util.List;
import java.util.Map;

public interface ReservationService {
//    ReservationDto reserve(ReservationDto reservationDto);

    ReservationDto getReservation(Long id);

    ReservationDto updateReservation(Long id, ReservationDto reservationDto);
    ReservationDto createReservationWithTransaction(ReservationDto reservationDto, String token);
    List<ReservationDto> findReservationsByEmployeId(Long employeId);
    void deleteReservation(Long id);

    Long countTotalReservations();

    List<Object[]> getMontantSumByEmploye();

    List<ReservationDto> getAllReservations();
}
