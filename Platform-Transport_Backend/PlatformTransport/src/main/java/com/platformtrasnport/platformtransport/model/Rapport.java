package com.platformtrasnport.platformtransport.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Rapport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenu;

    @Temporal(TemporalType.DATE)
    private Date date;


}
