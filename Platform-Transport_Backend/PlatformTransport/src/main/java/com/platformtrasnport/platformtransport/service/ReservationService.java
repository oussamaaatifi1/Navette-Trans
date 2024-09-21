package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    ReservationDto reserve(ReservationDto reservationDto);

    ReservationDto getReservation(Long id);

    ReservationDto updateReservation(Long id, ReservationDto reservationDto);

    void deleteReservation(Long id);

    List<ReservationDto> getAllReservations();
}
