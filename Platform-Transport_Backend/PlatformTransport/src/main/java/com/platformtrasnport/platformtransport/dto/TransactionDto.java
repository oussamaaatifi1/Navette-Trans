package com.platformtrasnport.platformtransport.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class TransactionDto {
    private Long id;
    private float montant;
    private Date date;
    private Long employeId;
    private Long reservationId;
}