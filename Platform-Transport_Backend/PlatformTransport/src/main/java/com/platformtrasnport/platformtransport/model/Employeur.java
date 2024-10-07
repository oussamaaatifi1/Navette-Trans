package com.platformtrasnport.platformtransport.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("Employeur")
public class Employeur extends Utilisateur implements Serializable {

    @OneToMany(mappedBy = "employeur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private List<OffreTransport> offreTransportList;


}
