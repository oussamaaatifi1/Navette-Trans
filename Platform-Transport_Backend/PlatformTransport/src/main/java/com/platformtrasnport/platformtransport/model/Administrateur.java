package com.platformtrasnport.platformtransport.model;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("Administrateur")
public class Administrateur extends Utilisateur {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "administrateur_id")
    private Administrateur admin;


}
