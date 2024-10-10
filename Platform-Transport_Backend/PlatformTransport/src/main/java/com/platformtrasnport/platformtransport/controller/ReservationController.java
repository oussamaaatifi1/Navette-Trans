package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.dto.ReservationDto;
import com.platformtrasnport.platformtransport.service.impl.ReservationServiceImpl;
import com.platformtrasnport.platformtransport.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

    private final ReservationServiceImpl reservationService;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    public ReservationController(ReservationServiceImpl reservationService, JwtService jwtService) {
        this.reservationService = reservationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('EMPLOYE')")
    public ResponseEntity<ReservationDto> createReservationWithTransaction(
            @RequestBody ReservationDto reservationDto,
            @RequestHeader("Authorization") String token) {
        logger.info("Incoming reservationDto: {}", reservationDto); // Using logger instead of System.out

        ReservationDto savedReservation = reservationService.createReservationWithTransaction(reservationDto, token);
        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable Long id) {
        ReservationDto reservation = reservationService.getReservation(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/offreByEmployeur")
    @PreAuthorize("hasRole('EMPLOYEUR')")
    public ResponseEntity<List<ReservationDto>> getReservationByEmployeur(@RequestHeader("Authorization") String token) {
        if (!token.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Format du token invalide");
        }
        String jwtToken = token.substring(7);
        Long userId = jwtService.extractUserId(jwtToken);
        List<ReservationDto> offres = reservationService.findReservationsByEmployeId(userId);
        return ResponseEntity.ok(offres);
    }

    @GetMapping("/reservationsByEmploye/{employeId}")
    @PreAuthorize("hasRole('EMPLOYEUR')")
    public ResponseEntity<List<ReservationDto>> getReservationsByEmployeId(@PathVariable Long employeId, Authentication authentication) {
        authentication.getAuthorities().forEach(authority -> logger.info("Authority: {}", authority.getAuthority())); // Logging authorities

        List<ReservationDto> reservations = reservationService.findReservationsByEmployeId(employeId);
        if (reservations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('EMPLOYEUR')")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYE')")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        ReservationDto updatedReservation = reservationService.updateReservation(id, reservationDto);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYE')")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count-by-employe")
    @PreAuthorize("hasAnyAuthority('EMPLOYEUR','ADMIN')")
    public Long countTotalReservations() {
        return reservationService.countTotalReservations();
    }

    @GetMapping("/sum-by-employe")
    @PreAuthorize("hasAnyAuthority('EMPLOYEUR', 'ADMIN')")
    public List<Object[]> sumMontantByEmploye() {
        return reservationService.getMontantSumByEmploye();
    }
}