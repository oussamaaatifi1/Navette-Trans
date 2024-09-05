    package com.platformtrasnport.platformtransport.model;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.util.Date;

    @Entity
    @Getter
    @Setter
    public class Transaction {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private float montant;

        @Temporal(TemporalType.DATE)
        private Date date;

        @ManyToOne
        @JoinColumn(name = "utilisateur_id")
        private Employe employe;

        @OneToOne(mappedBy = "transaction")
        private Reservation reservation;
    }
