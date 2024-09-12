package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.ReservationDto;
import com.platformtrasnport.platformtransport.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(source = "employe.id", target = "employeId")
    @Mapping(source = "offre.id", target = "offreId")
    @Mapping(source = "transaction.id", target = "transactionId")
    ReservationDto reservationToDto(Reservation reservation);

    @Mapping(target = "employe", ignore = true)
    @Mapping(target = "offre", ignore = true)
    @Mapping(target = "transaction", ignore = true)
    Reservation dtoToReservation(ReservationDto dto);
}