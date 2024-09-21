package com.platformtrasnport.platformtransport.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("Employeur")
public class Employeur extends Utilisateur {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "offretansport_id")
    private List<OffreTransport> offreTransport;


}
