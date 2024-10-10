package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.dto.ReservationDto;

import java.util.List;

public interface ReservationService {

    ReservationDto getReservation(Long id);

    ReservationDto updateReservation(Long id, ReservationDto reservationDto);
    ReservationDto createReservationWithTransaction(ReservationDto reservationDto, String token);
    List<ReservationDto> findReservationsByEmployeId(Long employeId);
    void deleteReservation(Long id);

    Long countTotalReservations();

    List<Object[]> getMontantSumByEmploye();

    List<ReservationDto> getAllReservations();
}