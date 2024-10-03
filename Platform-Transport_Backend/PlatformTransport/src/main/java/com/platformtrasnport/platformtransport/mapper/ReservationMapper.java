package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.ReservationDto;
import com.platformtrasnport.platformtransport.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "employe.id", target = "employeId")
    @Mapping(source = "offre.id", target = "offreId")
    @Mapping(source = "offre.pointDepart", target = "pointDepart") // Map pointDepart from offreTransport
    @Mapping(source = "offre.destination", target = "destination") // Map destination from offreTransport
    @Mapping(source = "transaction.id", target = "transactionId")
    @Mapping(source = "transaction.montant", target = "montant")
    @Mapping(source = "nombrePlaces", target = "nombrePlaces")
    ReservationDto reservationToDto(Reservation reservation);

    @Mapping(target = "employe", ignore = true)
    @Mapping(target = "offre", ignore = true) // You can update this if you map the offre entity manually
    @Mapping(target = "transaction", ignore = true)
    @Mapping(source = "nombrePlaces", target = "nombrePlaces")
    Reservation dtoToReservation(ReservationDto reservationDto);
}
