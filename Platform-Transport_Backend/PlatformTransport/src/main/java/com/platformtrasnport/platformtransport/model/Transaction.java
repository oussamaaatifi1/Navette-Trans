package com.platformtrasnport.platformtransport.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float montant;

    @Temporal(TemporalType.DATE)
    private Date date;

    // Assuming that the foreign key refers to the employee who made the transaction.
    @ManyToOne
    @JoinColumn(name = "employe_id", nullable = false)  // Corrected FK name and made it non-nullable
    private Employe employe;

    // Bidirectional mapping with Reservation
    @OneToOne(mappedBy = "transaction")
    private Reservation reservation;
}
