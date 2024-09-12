package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.AdministrateurDto;
import com.platformtrasnport.platformtransport.model.Administrateur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class})
public interface AdministrateurMapper {
    AdministrateurMapper INSTANCE = Mappers.getMapper(AdministrateurMapper.class);

    AdministrateurDto administrateurToDto(Administrateur administrateur);
    Administrateur dtoToAdministrateur(AdministrateurDto dto);
}