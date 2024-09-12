package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.OffreTransportDto;
import com.platformtrasnport.platformtransport.model.OffreTransport;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface OffreTransportMapper {
    OffreTransportMapper INSTANCE = Mappers.getMapper(OffreTransportMapper.class);

    OffreTransportDto offreTransportToDto(OffreTransport offreTransport);
    OffreTransport dtoToOffreTransport(OffreTransportDto dto);
}