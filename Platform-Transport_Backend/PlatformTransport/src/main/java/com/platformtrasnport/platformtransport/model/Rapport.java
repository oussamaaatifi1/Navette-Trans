package com.platformtrasnport.platformtransport.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
public class Rapport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenu;

    @Temporal(TemporalType.DATE)
    private LocalDate dateRapport;

    @ManyToOne
    @JoinColumn(name = "administrateur_id")
    private Administrateur administrateur;
}
