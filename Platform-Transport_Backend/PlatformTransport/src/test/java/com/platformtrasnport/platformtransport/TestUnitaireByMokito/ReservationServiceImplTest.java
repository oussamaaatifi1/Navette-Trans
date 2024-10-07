package com.platformtrasnport.platformtransport.TestUnitaireByMokito;

import com.platformtrasnport.platformtransport.dto.ReservationDto;
import com.platformtrasnport.platformtransport.mapper.ReservationMapper;
import com.platformtrasnport.platformtransport.model.*;
import com.platformtrasnport.platformtransport.repository.*;
import com.platformtrasnport.platformtransport.service.JwtService;
import com.platformtrasnport.platformtransport.service.impl.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private EmployeRepository employeRepository;

    @Mock
    private OffreTransportRepository offreTransportRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ReservationMapper reservationMapper;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateReservationWithTransaction() {
        // Prepare data
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setOffreId(1L);
        reservationDto.setNombrePlaces(3);

        Employe employe = new Employe();
        employe.setId(1L);

        OffreTransport offre = new OffreTransport();
        offre.setId(1L);
        offre.setPrix(100.0f);
        offre.setNombrePlaces(5);

        Reservation reservation = new Reservation();
        Transaction transaction = new Transaction();

        when(jwtService.extractUserId(anyString())).thenReturn(1L);
        when(employeRepository.findById(anyLong())).thenReturn(Optional.of(employe));
        when(offreTransportRepository.findById(anyLong())).thenReturn(Optional.of(offre));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(reservationMapper.reservationToDto(any(Reservation.class))).thenReturn(reservationDto);

        // Call service method
        ReservationDto result = reservationService.createReservationWithTransaction(reservationDto, "Bearer token");

        // Verify
        assertNotNull(result);
        assertEquals(1L, result.getOffreId());
        verify(offreTransportRepository, times(1)).save(any(OffreTransport.class));
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void testCreateReservationWithTransaction_ThrowsExceptionIfNotEnoughSeats() {
        // Prepare data
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setOffreId(1L);
        reservationDto.setNombrePlaces(10); // More than available seats

        OffreTransport offre = new OffreTransport();
        offre.setId(1L);
        offre.setNombrePlaces(5); // Only 5 available

        when(jwtService.extractUserId(anyString())).thenReturn(1L);
        when(employeRepository.findById(anyLong())).thenReturn(Optional.of(new Employe()));
        when(offreTransportRepository.findById(anyLong())).thenReturn(Optional.of(offre));

        // Call service method and expect exception
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> reservationService.createReservationWithTransaction(reservationDto, "Bearer token"));

        assertEquals("400 BAD_REQUEST \"Not enough available seats\"", exception.getMessage());
    }

    @Test
    void testFindReservationsByEmployeId() {
        // Prepare data
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        List<Reservation> reservations = Arrays.asList(reservation);

        when(reservationRepository.findReservationsByEmployeId(anyLong())).thenReturn(reservations);

        // Call service method
        List<ReservationDto> result = reservationService.findReservationsByEmployeId(1L);

        // Verify
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(reservationRepository, times(1)).findReservationsByEmployeId(anyLong());
    }

    @Test
    void testGetReservation_Success() {
        // Prepare data
        Reservation reservation = new Reservation();
        reservation.setId(1L);

        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservation));
        when(reservationMapper.reservationToDto(any(Reservation.class))).thenReturn(new ReservationDto());

        // Call service method
        ReservationDto result = reservationService.getReservation(1L);

        // Verify
        assertNotNull(result);
        verify(reservationRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetReservation_NotFound() {
        // Prepare data
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Call service method and expect exception
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> reservationService.getReservation(1L));

        assertEquals("404 NOT_FOUND \"Reservation not found\"", exception.getMessage());
    }

    @Test
    void testUpdateReservation_Success() {
        // Prepare ReservationDto
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setEmployeId(1L);
        reservationDto.setOffreId(1L);
        reservationDto.setMontant(500.0f);
        reservationDto.setDateReservation(LocalDate.now());

        // Prepare mock entities
        Reservation reservation = new Reservation();
        reservation.setId(1L);

        Employe employe = new Employe();
        employe.setId(1L);

        OffreTransport offre = new OffreTransport();
        offre.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setMontant(400.0f);  // Initial different montant
        reservation.setTransaction(transaction);

        // Mock repository calls
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservation));
        when(employeRepository.findById(1L)).thenReturn(Optional.of(employe));
        when(offreTransportRepository.findById(1L)).thenReturn(Optional.of(offre));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        when(reservationMapper.reservationToDto(any(Reservation.class))).thenReturn(reservationDto);

        // Call service method
        ReservationDto result = reservationService.updateReservation(1L, reservationDto);

        // Verify the result
        assertNotNull(result);
        assertEquals(1L, result.getEmployeId());
        assertEquals(1L, result.getOffreId());
        assertEquals(500.0f, result.getMontant());

        // Verify that the transaction was updated
        assertEquals(500.0f, reservation.getTransaction().getMontant());

        // Verify interactions with repositories and mapper
        verify(reservationRepository, times(1)).findById(1L);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(reservationMapper, times(1)).reservationToDto(any(Reservation.class));
    }

    @Test
    void testDeleteReservation_Success() {
        // Prepare data
        Reservation reservation = new Reservation();
        OffreTransport offre = new OffreTransport();
        reservation.setOffre(offre);

        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservation));

        // Call service method
        reservationService.deleteReservation(1L);

        // Verify
        verify(reservationRepository, times(1)).delete(any(Reservation.class));
        verify(offreTransportRepository, times(1)).save(any(OffreTransport.class));
    }
}
//package com.platformtrasnport.platformtransport.TestUnitaireByMokito;
//
//import com.platformtrasnport.platformtransport.dto.ReservationDto;
//import com.platformtrasnport.platformtransport.mapper.ReservationMapper;
//import com.platformtrasnport.platformtransport.model.*;
//import com.platformtrasnport.platformtransport.repository.*;
//import com.platformtrasnport.platformtransport.service.JwtService;
//import com.platformtrasnport.platformtransport.service.impl.ReservationServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.time.LocalDate;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ReservationServiceImplTest {
//
//    @InjectMocks
//    private ReservationServiceImpl reservationService;
//
//    @Mock
//    private ReservationRepository reservationRepository;
//
//    @Mock
//    private EmployeRepository employeRepository;
//
//    @Mock
//    private OffreTransportRepository offreTransportRepository;
//
//    @Mock
//    private TransactionRepository transactionRepository;
//
//    @Mock
//    private ReservationMapper reservationMapper;
//
//    @Mock
//    private JwtService jwtService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testCreateReservationWithTransaction_Success() {
//        // Arrange
//        String token = "Bearer token";
//        ReservationDto reservationDto = new ReservationDto();
//        reservationDto.setOffreId(1L);
//        reservationDto.setNombrePlaces(2);
//        reservationDto.setDateReservation(LocalDate.now());
//
//        Employe employe = new Employe();
//        employe.setId(1L);
//
//        OffreTransport offre = new OffreTransport();
//        offre.setId(1L);
//        offre.setNombrePlaces(5);
//        offre.setPrix(100);
//        offre.setPointDepart("Point A");
//        offre.setDestination("Point B");
//
//        Reservation reservation = new Reservation();
//        reservation.setId(1L);
//        reservation.setEmploye(employe);
//        reservation.setOffre(offre);
//        reservation.setNombrePlaces(2);
//        reservation.setDateReservation(LocalDate.now());
//        reservation.setPointDepart("Point A");
//        reservation.setDestination("Point B");
//
//        Transaction transaction = new Transaction();
//        transaction.setId(1L);
//        transaction.setMontant(200);
//        transaction.setEmploye(employe);
//        transaction.setDate(new Date());
//        transaction.setReservation(reservation);
//
//        when(jwtService.extractUserId(token.substring(7))).thenReturn(1L);
//        when(employeRepository.findById(1L)).thenReturn(Optional.of(employe));
//        when(offreTransportRepository.findById(1L)).thenReturn(Optional.of(offre));
//        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
//        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
//        when(reservationMapper.reservationToDto(any(Reservation.class))).thenReturn(reservationDto);
//
//        // Act
//        ReservationDto result = reservationService.createReservationWithTransaction(reservationDto, token);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1L, result.getEmployeId());
//        assertEquals("Point A", result.getPointDepart());
//        assertEquals("Point B", result.getDestination());
//        assertEquals(200.0, result.getMontant());
//    }
//
//    @Test
//    void testCreateReservationWithTransaction_EmployeNotFound() {
//        // Arrange
//        String token = "Bearer token";
//        ReservationDto reservationDto = new ReservationDto();
//        reservationDto.setOffreId(1L);
//        reservationDto.setNombrePlaces(2);
//
//        when(jwtService.extractUserId(token.substring(7))).thenReturn(1L);
//        when(employeRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
//                reservationService.createReservationWithTransaction(reservationDto, token));
//        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
//        assertEquals("Employe not found", exception.getReason());
//    }
//
//    @Test
//    void testGetReservation_Success() {
//        // Arrange
//        Reservation reservation = new Reservation();
//        reservation.setId(1L);
//        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
//        when(reservationMapper.reservationToDto(reservation)).thenReturn(new ReservationDto());
//
//        // Act
//        ReservationDto result = reservationService.getReservation(1L);
//
//        // Assert
//        assertNotNull(result);
//        verify(reservationRepository).findById(1L);
//    }
//
//    @Test
//    void testGetReservation_NotFound() {
//        // Arrange
//        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
//                reservationService.getReservation(1L));
//        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
//        assertEquals("Reservation not found", exception.getReason());
//    }
//
//    @Test
//    void testUpdateReservation_Success() {
//        // Arrange
//        Long reservationId = 1L;
//        ReservationDto reservationDto = new ReservationDto();
//        reservationDto.setDateReservation(LocalDate.now());
//
//        Reservation existingReservation = new Reservation();
//        existingReservation.setId(reservationId);
//        existingReservation.setDateReservation(LocalDate.now().minusDays(1));
//
//        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(existingReservation));
//        when(reservationMapper.reservationToDto(any(Reservation.class))).thenReturn(reservationDto);
//        when(reservationRepository.save(existingReservation)).thenReturn(existingReservation);
//
//        // Act
//        ReservationDto result = reservationService.updateReservation(reservationId, reservationDto);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(existingReservation.getDateReservation(), result.getDateReservation());
//    }
//
//    @Test
//    void testUpdateReservation_NotFound() {
//        // Arrange
//        Long reservationId = 1L;
//        ReservationDto reservationDto = new ReservationDto();
//
//        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
//                reservationService.updateReservation(reservationId, reservationDto));
//        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
//        assertEquals("Reservation not found", exception.getReason());
//    }
//
//    @Test
//    void testDeleteReservation_Success() {
//        // Arrange
//        Long reservationId = 1L;
//        Reservation reservation = new Reservation();
//        reservation.setId(reservationId);
//        reservation.setTransaction(new Transaction());
//        reservation.setOffre(new OffreTransport());
//
//        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
//        when(offreTransportRepository.save(any(OffreTransport.class))).thenReturn(reservation.getOffre());
//
//        // Act
//        reservationService.deleteReservation(reservationId);
//
//        // Assert
//        verify(reservationRepository).delete(reservation);
//        verify(transactionRepository).delete(reservation.getTransaction());
//    }
//
//    @Test
//    void testDeleteReservation_NotFound() {
//        // Arrange
//        Long reservationId = 1L;
//        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
//                reservationService.deleteReservation(reservationId));
//        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
//        assertEquals("Reservation not found", exception.getReason());
//    }
//
//    @Test
//    void testGetAllReservations() {
//        // Arrange
//        Reservation reservation = new Reservation();
//        when(reservationRepository.findAll()).thenReturn(Collections.singletonList(reservation));
//        when(reservationMapper.reservationToDto(reservation)).thenReturn(new ReservationDto());
//
//        // Act
//        List<ReservationDto> result = reservationService.getAllReservations();
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.size());
//    }
//}
