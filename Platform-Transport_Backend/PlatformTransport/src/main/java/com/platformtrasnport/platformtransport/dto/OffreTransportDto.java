package com.platformtrasnport.platformtransport.dto;

import com.platformtrasnport.platformtransport.model.enul.OffreStatus;
import com.platformtrasnport.platformtransport.model.enul.TypeOffreTransport;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OffreTransportDto {
    private Long id;
    private TypeOffreTransport typeOffreTransport;
    private String pointDepart;
    private String destination;
    private LocalDate dateOffre;
    private int nombrePlaces;
    private float prix;
    private String imgUrl;
    private OffreStatus status;
    private Long employeurId;
}
