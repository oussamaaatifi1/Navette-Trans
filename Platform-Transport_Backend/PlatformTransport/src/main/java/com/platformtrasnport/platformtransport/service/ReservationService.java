package com.platformtrasnport.platformtransport.service;


import com.platformtrasnport.platformtransport.model.Reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    //    public Reservation ajoute(Long employeId, Long offreTransportId);
    public Reservation reserve(Reservation reservation);

    public Reservation getReservation(Long id);

    public Reservation updateReservation(Long id, Reservation reservation);

    public void deleteReservation(Long id);

    public List<Reservation> getAllReservations();
}