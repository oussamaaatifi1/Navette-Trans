package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.OffreTransportDto;
import com.platformtrasnport.platformtransport.model.OffreTransport;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OffreTransportMapper {
    OffreTransportDto offreTransportToDto(OffreTransport offreTransport);
    OffreTransport dtoToOffreTransport(OffreTransportDto dto);
}
