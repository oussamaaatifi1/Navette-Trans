package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.dto.ReservationDto;
import com.platformtrasnport.platformtransport.mapper.ReservationMapper;
import com.platformtrasnport.platformtransport.model.Employe;
import com.platformtrasnport.platformtransport.model.OffreTransport;
import com.platformtrasnport.platformtransport.model.Reservation;
import com.platformtrasnport.platformtransport.model.Transaction;
import com.platformtrasnport.platformtransport.repository.EmployeRepository;
import com.platformtrasnport.platformtransport.repository.OffreTransportRepository;
import com.platformtrasnport.platformtransport.repository.ReservationRepository;
import com.platformtrasnport.platformtransport.repository.TransactionRepository;
import com.platformtrasnport.platformtransport.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private OffreTransportRepository offreTransportRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public ReservationDto reserve(ReservationDto reservationDto) {
        Reservation reservation = reservationMapper.dtoToReservation(reservationDto);

        // Fetch and set related entities based on IDs
        if (reservationDto.getEmployeId() != null) {
            Employe employe = employeRepository.findById(reservationDto.getEmployeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employe not found"));
            reservation.setEmploye(employe);
        }

        if (reservationDto.getOffreId() != null) {
            OffreTransport offre = offreTransportRepository.findById(reservationDto.getOffreId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OffreTransport not found"));
            reservation.setOffre(offre);

            if (offre.getNombrePlaces() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No available seats");
            }
            offre.setNombrePlaces(offre.getNombrePlaces() - 1);
            offreTransportRepository.save(offre);
        }

        if (reservationDto.getTransactionId() != null) {
            Transaction transaction = transactionRepository.findById(reservationDto.getTransactionId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
            reservation.setTransaction(transaction);
        }

        reservation.setDateReservation(LocalDate.now());

        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.reservationToDto(savedReservation);
    }

    @Override
    public ReservationDto getReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
        return reservationMapper.reservationToDto(reservation);
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(reservationMapper::reservationToDto)
                .toList();
    }

    @Override
    public ReservationDto updateReservation(Long id, ReservationDto reservationDto) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        // Update fields
        existingReservation.setDateReservation(reservationDto.getDateReservation());

        if (reservationDto.getEmployeId() != null) {
            Employe employe = employeRepository.findById(reservationDto.getEmployeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employe not found"));
            existingReservation.setEmploye(employe);
        }

        if (reservationDto.getOffreId() != null) {
            OffreTransport offre = offreTransportRepository.findById(reservationDto.getOffreId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OffreTransport not found"));
            existingReservation.setOffre(offre);

            // Update seats in OffreTransport
            if (offre.getNombrePlaces() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No available seats");
            }
            offre.setNombrePlaces(offre.getNombrePlaces() - 1);
            offreTransportRepository.save(offre);
        }

        if (reservationDto.getTransactionId() != null) {
            Transaction transaction = transactionRepository.findById(reservationDto.getTransactionId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
            existingReservation.setTransaction(transaction);
        }

        Reservation updatedReservation = reservationRepository.save(existingReservation);
        return reservationMapper.reservationToDto(updatedReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found");
        }
        reservationRepository.deleteById(id);
    }
}
