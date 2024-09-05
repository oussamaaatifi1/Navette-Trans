    package com.platformtrasnport.platformtransport.model;

    import jakarta.persistence.DiscriminatorValue;
    import jakarta.persistence.Entity;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.Setter;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @DiscriminatorValue("Employe")
    public class Employe extends Utilisateur{
    }
