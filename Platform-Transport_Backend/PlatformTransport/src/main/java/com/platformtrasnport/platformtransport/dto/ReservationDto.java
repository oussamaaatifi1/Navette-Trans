package com.platformtrasnport.platformtransport.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class ReservationDto {
    private Long id;
    private LocalDate dateReservation;
    private Long employeId;
    private Long offreId;
    private Long transactionId;
}