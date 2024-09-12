package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.EmployeDto;
import com.platformtrasnport.platformtransport.model.Employe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class})
public interface EmployeMapper {
    EmployeMapper INSTANCE = Mappers.getMapper(EmployeMapper.class);

    EmployeDto employeToDto(Employe employe);
    Employe dtoToEmploye(EmployeDto dto);
}