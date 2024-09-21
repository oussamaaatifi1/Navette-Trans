    package com.platformtrasnport.platformtransport.controller;

    import com.platformtrasnport.platformtransport.dto.ReservationDto;
    import com.platformtrasnport.platformtransport.model.Reservation;
    import com.platformtrasnport.platformtransport.service.Impl.ReservationServiceImpl;
    import com.platformtrasnport.platformtransport.service.ReservationService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/reservations")
    @CrossOrigin(origins = "http://localhost:4200")
    public class ReservationController {
        @Autowired
        private ReservationServiceImpl reservationService;

        @PostMapping("/ajoute")
        public ResponseEntity<ReservationDto> reserve(@RequestBody ReservationDto reservationDto) {
            return ResponseEntity.ok(reservationService.reserve(reservationDto));
        }

        @GetMapping("/{id}")
        public ResponseEntity<ReservationDto> getReservation(@PathVariable Long id) {
            return ResponseEntity.ok(reservationService.getReservation(id));
        }

        @GetMapping
        public List<ReservationDto> getAllReservations() {
            return reservationService.getAllReservations();
        }

        @PutMapping("/{id}")
        public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
            return ResponseEntity.ok(reservationService.updateReservation(id, reservationDto));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
            reservationService.deleteReservation(id);
            return ResponseEntity.ok().build();
        }
    }
