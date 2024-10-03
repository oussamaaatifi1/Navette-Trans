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
    private int nombrePlaces;

    private String pointDepart;
    private String destination;
    @ManyToOne
    @JoinColumn(name = "employe_id", nullable = false)  // Corrected FK name to match `Employe` entity
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "offre_id", nullable = false)    // Added nullable=false to ensure integrity
    private OffreTransport offre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;
}
