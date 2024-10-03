package com.platformtrasnport.platformtransport.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EmployeDto extends UtilisateurDto {
        private String phone;
        private String address;
        private String dateOfBirth;
}