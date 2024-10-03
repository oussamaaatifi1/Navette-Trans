package com.platformtrasnport.platformtransport.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeDto extends UtilisateurDto {
        private String phone;
        private String address;
        private String dateOfBirth;
}