    package com.platformtrasnport.platformtransport.controller;

    import com.platformtrasnport.platformtransport.model.Reservation;
    import com.platformtrasnport.platformtransport.service.Impl.ReservationServiceImpl;
    import com.platformtrasnport.platformtransport.service.ReservationService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/reservations")
    public class ReservationController {
        @Autowired
        private ReservationServiceImpl reservationService;

        @PostMapping("/ajoute")
        public ResponseEntity<Reservation> reserve(@RequestBody Reservation reservation) {
            return ResponseEntity.ok(reservationService.reserve(reservation));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
            return ResponseEntity.ok(reservationService.getReservation(id));
        }

        @GetMapping
        public List<Reservation> getAllReservations() {
            return reservationService.getAllReservations();
        }

        @PutMapping("/{id}")
        public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
            return ResponseEntity.ok(reservationService.updateReservation(id, reservation));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
            reservationService.deleteReservation(id);
            return ResponseEntity.ok().build();
        }
    }