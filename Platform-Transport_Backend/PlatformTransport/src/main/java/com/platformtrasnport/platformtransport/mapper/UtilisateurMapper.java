package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.*;
import com.platformtrasnport.platformtransport.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UtilisateurMapper {
    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    UtilisateurDto utilisateurToDto(Utilisateur utilisateur);
    Utilisateur dtoToUtilisateur(UtilisateurDto dto);
}
