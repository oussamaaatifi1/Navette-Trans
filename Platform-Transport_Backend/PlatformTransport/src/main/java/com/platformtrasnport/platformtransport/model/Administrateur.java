package com.platformtrasnport.platformtransport.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("Administrateur")
public class Administrateur extends Utilisateur {

}
