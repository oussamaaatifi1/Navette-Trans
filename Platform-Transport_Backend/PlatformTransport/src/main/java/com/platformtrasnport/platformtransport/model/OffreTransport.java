package com.platformtrasnport.platformtransport.model;

import com.platformtrasnport.platformtransport.model.enul.TypeOffreTransport;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class OffreTransport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeOffreTransport type;

    private String pointDepart;
    private String destination;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int nombrePlaces;
    private float prix;


}