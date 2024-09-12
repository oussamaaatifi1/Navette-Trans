package com.platformtrasnport.platformtransport.dto;

import com.platformtrasnport.platformtransport.model.enul.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UtilisateurDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String imgUrl;
    private Role role;
}