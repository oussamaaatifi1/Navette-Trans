package com.platformtrasnport.platformtransport.model;

import jakarta.persistence.Entity;

import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue("Particulier")
public class Particulier extends Utilisateur{
}
