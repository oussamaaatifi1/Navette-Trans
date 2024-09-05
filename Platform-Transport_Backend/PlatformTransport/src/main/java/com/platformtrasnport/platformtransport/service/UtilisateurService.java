package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.model.Employe;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    List<Employe> getAllUtilisateurs();
    public Employe createUtilisateur(Employe employe);
    public Optional<Employe> getUtilisateurById(Long id);
    public Optional<Employe> updateUtilisateur(Long id, Employe employe);
    public boolean deleteUtilisateur(Long id);

}
