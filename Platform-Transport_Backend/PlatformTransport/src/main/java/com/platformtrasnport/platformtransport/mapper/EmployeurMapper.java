package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.EmployeurDto;
import com.platformtrasnport.platformtransport.model.Employeur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeurMapper {
    EmployeurDto employeurToDto(Employeur employeur);
    Employeur dtoToEmployeur(EmployeurDto dto);
}

