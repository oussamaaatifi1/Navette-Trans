package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.RapportDto;
import com.platformtrasnport.platformtransport.model.Rapport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RapportMapper {
    RapportMapper INSTANCE = Mappers.getMapper(RapportMapper.class);

    @Mapping(source = "administrateur.id", target = "administrateurId")
    RapportDto rapportToDto(Rapport rapport);

    @Mapping(source = "administrateurId", target = "administrateur.id")
    Rapport dtoToRapport(RapportDto dto);
}
