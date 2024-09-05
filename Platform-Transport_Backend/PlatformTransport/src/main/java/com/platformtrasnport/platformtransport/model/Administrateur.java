package com.platformtrasnport.platformtransport.model;

import jakarta.persistence.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue("Administrateur")
public class Administrateur extends Utilisateur {
}
