    package com.platformtrasnport.platformtransport.model;

    import jakarta.persistence.Column;
    import jakarta.persistence.DiscriminatorValue;
    import jakarta.persistence.Entity;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.List;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @DiscriminatorValue("Employe")
    public class Employe extends Utilisateur{

        private String phone;
        private String address;
        private String dateOfBirth;

    }
