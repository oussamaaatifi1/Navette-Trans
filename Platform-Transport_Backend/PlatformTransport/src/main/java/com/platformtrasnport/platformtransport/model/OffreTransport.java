    package com.platformtrasnport.platformtransport.model;

    import com.platformtrasnport.platformtransport.model.enul.OffreStatus;
    import com.platformtrasnport.platformtransport.model.enul.RejectionReason;
    import com.platformtrasnport.platformtransport.model.enul.TypeOffreTransport;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.time.LocalDate;
    import java.util.Date;

    @Entity
    @Getter
    @Setter
    public class OffreTransport {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Enumerated(EnumType.STRING)
        private TypeOffreTransport typeOffreTransport;

        private String pointDepart;
        private String destination;

        private LocalDate dateOffre;

        private int nombrePlaces;
        private float prix;
        private String imgUrl;
        private OffreStatus status;
//      private RejectionReason rejectionReason;

        @ManyToOne
        @JoinColumn(name = "employeur_id")
        private Employeur employeur;

    }