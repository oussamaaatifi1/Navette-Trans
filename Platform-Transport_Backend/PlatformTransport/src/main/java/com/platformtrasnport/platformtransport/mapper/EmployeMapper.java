package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.EmployeDto;
import com.platformtrasnport.platformtransport.model.Employe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UtilisateursMapper.class)
public interface EmployeMapper {
    EmployeDto employeToDto(Employe employe);
    Employe dtoToEmploye(EmployeDto employeDto);
}
