package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.AdministrateurDto;
import com.platformtrasnport.platformtransport.model.Administrateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdministrateurMapper {
    AdministrateurDto toDto(Administrateur administrateur);
    Administrateur toEntity(AdministrateurDto administrateurDto);
}
