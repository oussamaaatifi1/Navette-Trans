package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.dto.ReservationDto;
import com.platformtrasnport.platformtransport.mapper.ReservationMapper;
import com.platformtrasnport.platformtransport.model.*;
import com.platformtrasnport.platformtransport.repository.*;
import com.platformtrasnport.platformtransport.service.JwtService;
import com.platformtrasnport.platformtransport.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final EmployeRepository employeRepository;

    private final OffreTransportRepository offreTransportRepository;

    private final TransactionRepository transactionRepository;

    private final ReservationMapper reservationMapper;

    private final JwtService jwtService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, EmployeRepository employeRepository, OffreTransportRepository offreTransportRepository, TransactionRepository transactionRepository, ReservationMapper reservationMapper, JwtService jwtService) {
        this.reservationRepository = reservationRepository;
        this.employeRepository = employeRepository;
        this.offreTransportRepository = offreTransportRepository;
        this.transactionRepository = transactionRepository;
        this.reservationMapper = reservationMapper;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public ReservationDto createReservationWithTransaction(ReservationDto reservationDto, String token) {
        // Extract employee ID from token
        Long employeId = jwtService.extractUserId(token.substring(7));
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employe not found"));

        // Get the transport offer
        OffreTransport offre = offreTransportRepository.findById(reservationDto.getOffreId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OffreTransport not found"));

        // Check available seats
        if (offre.getNombrePlaces() < reservationDto.getNombrePlaces()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough available seats");
        }

        // Calculate total price
        float totalPrice = offre.getPrix() * reservationDto.getNombrePlaces(); // Ensure prix is also of type double

        // Create reservation
        Reservation reservation = new Reservation();
        reservation.setEmploye(employe);
        reservation.setOffre(offre);
        reservation.setDateReservation(reservationDto.getDateReservation() != null ? reservationDto.getDateReservation() : LocalDate.now());
        reservation.setNombrePlaces(reservationDto.getNombrePlaces());

        // Set pointDepart and destination from the OffreTransport
        reservation.setPointDepart(offre.getPointDepart());
        reservation.setDestination(offre.getDestination());

        // Update available seats
        offre.setNombrePlaces(offre.getNombrePlaces() - reservationDto.getNombrePlaces());
        offreTransportRepository.save(offre);

        // Create and set the transaction
        Transaction transaction = new Transaction();
        transaction.setEmploye(employe);
        transaction.setMontant(totalPrice); // Ensure montant is also of type double
        transaction.setDate(new Date());  // Using java.util.Date as per TransactionDto
        transaction.setReservation(reservation);

        reservation.setTransaction(transaction);

        // Save the reservation
        Reservation savedReservation = reservationRepository.save(reservation);

        // Update and save the transaction
        transaction.setReservation(savedReservation);
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Create ReservationDto to return
        ReservationDto savedReservationDto = reservationMapper.reservationToDto(savedReservation);
        savedReservationDto.setMontant(totalPrice);
        savedReservationDto.setTransactionId(savedTransaction.getId());
        savedReservationDto.setEmployeId(employeId);

        // Include pointDepart and destination in the DTO
        savedReservationDto.setPointDepart(reservation.getPointDepart());
        savedReservationDto.setDestination(reservation.getDestination());

        return savedReservationDto;
    }




    @Override
    public List<ReservationDto> findReservationsByEmployeId(Long employeId) {
        List<Reservation> reservations = reservationRepository.findReservationsByEmployeId(employeId);
        return reservations.stream()
                .map(reservationMapper::reservationToDto)
                .collect(Collectors.toList());
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
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
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
        }

        // Update transaction if montant has changed
        if (existingReservation.getTransaction() != null &&
                existingReservation.getTransaction().getMontant() != reservationDto.getMontant()) {
            Transaction transaction = existingReservation.getTransaction();
            transaction.setMontant(reservationDto.getMontant());
            transactionRepository.save(transaction);
        }

        Reservation updatedReservation = reservationRepository.save(existingReservation);
        return reservationMapper.reservationToDto(updatedReservation);
    }

    @Override
    @Transactional
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        // Restore the seat in OffreTransport
        OffreTransport offre = reservation.getOffre();
        offre.setNombrePlaces(offre.getNombrePlaces() + 1);
        offreTransportRepository.save(offre);

        // Delete associated transaction if exists
        if (reservation.getTransaction() != null) {
            transactionRepository.delete(reservation.getTransaction());
        }

        reservationRepository.delete(reservation);
    }

    @Override
    public Long countTotalReservations() {
        return reservationRepository.countTotalReservations();
    }

    @Override
    public List<Object[]> getMontantSumByEmploye() {
        return reservationRepository.sumMontantByEmploye();
    }

}