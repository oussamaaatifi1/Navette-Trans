package com.platformtrasnport.platformtransport.mapper;

import com.platformtrasnport.platformtransport.dto.EmployeDto;
import com.platformtrasnport.platformtransport.dto.EmployeurDto;
import com.platformtrasnport.platformtransport.dto.UtilisateurDto;
import com.platformtrasnport.platformtransport.model.Employe;
import com.platformtrasnport.platformtransport.model.Employeur;
import com.platformtrasnport.platformtransport.model.Utilisateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateursMapper {
    UtilisateurDto toDto(Utilisateur utilisateur);

    Utilisateur toEntity(UtilisateurDto utilisateurDto);

    EmployeurDto employeurToDto(Employeur employeur);

    Employeur dtoToEmployeur(EmployeurDto employeurDto);

    EmployeDto employeToDto(Employe employe);  // Add this method

    Employe dtoToEmploye(EmployeDto employeDto);  // Add this method
}
