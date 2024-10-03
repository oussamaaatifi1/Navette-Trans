package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.EmployeurDto;
import com.platformtrasnport.platformtransport.model.Employeur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UtilisateursMapper.class)
public interface EmployeurMapper {
    EmployeurDto employeurToDto(Employeur employeur);
    Employeur dtoToEmployeur(EmployeurDto employeurDto);
}
