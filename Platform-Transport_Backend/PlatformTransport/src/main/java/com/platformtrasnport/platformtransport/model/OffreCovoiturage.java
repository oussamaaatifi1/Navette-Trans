package com.platformtrasnport.platformtransport.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class OffreCovoiturage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pointDepart;
    private String destination;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int nombrePlaces;
    private float prix;

}
