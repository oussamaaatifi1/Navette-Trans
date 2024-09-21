package com.platformtrasnport.platformtransport.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateReservation;

    // Foreign key for the employee making the reservation
    @ManyToOne
    @JoinColumn(name = "employe_id", nullable = false)  // Corrected FK name to match `Employe` entity
    private Employe employe;

    // Foreign key for the offer being reserved
    @ManyToOne
    @JoinColumn(name = "offre_id", nullable = false)    // Added nullable=false to ensure integrity
    private OffreTransport offre;

    // Foreign key for the transaction related to the reservation
    @OneToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;
}
