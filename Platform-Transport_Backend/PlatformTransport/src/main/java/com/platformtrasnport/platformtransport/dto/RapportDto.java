package com.platformtrasnport.platformtransport.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
@Getter
public class RapportDto {
    private Long id;
    private String contenu;
    private LocalDate dateRapport;
        private Long administrateurId;
}
