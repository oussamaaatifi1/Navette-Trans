package com.platformtrasnport.platformtransport.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class EmployeurDto extends UtilisateurDto {

    private List<OffreTransportDto> offreTransport;
}