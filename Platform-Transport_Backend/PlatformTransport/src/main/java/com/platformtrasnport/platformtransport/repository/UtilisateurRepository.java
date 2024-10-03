package com.platformtrasnport.platformtransport.repository;

import com.platformtrasnport.platformtransport.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Utilisateur findByEmail(String email);
    Utilisateur findByNom(String nom);
}