package com.platformtrasnport.platformtransport.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Temporal(TemporalType.DATE)
    private LocalDate dateReservation;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "offre_id")
    private OffreTransport offre;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

}