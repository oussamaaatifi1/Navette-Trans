import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OffreTransportService } from 'src/app/core/services/offre-transport.service';
import { OffreTransport } from 'src/app/core/models/OffreTransport';
import { ReservationDto } from 'src/app/core/models/ReservationDto';
import { AuthService } from 'src/app/core/services/auth.service';
import { ReservationService } from 'src/app/core/services/reservation.service';

@Component({
  selector: 'app-reserve-offre',
  templateUrl: './reserve-offre.component.html',
  styleUrls: ['./reserve-offre.component.css']
})
export class ReserveOffreComponent implements OnInit {
  updateForm: FormGroup;
  offer!: OffreTransport; // Assurez-vous que cette variable est correctement initialisée
  offreTransportId!: number;
  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private offreTransportService: OffreTransportService,
    private authService : AuthService,
    private reservationService : ReservationService,
  ) {
    this.updateForm = this.fb.group({
      typeOffreTransport: ['', Validators.required],
      pointDepart: ['', Validators.required],
      destination: ['', Validators.required],
      dateOffre: ['', Validators.required],
      nombrePlaces: ['', [Validators.required, Validators.min(1)]],
      prix: ['', [Validators.required, Validators.min(0)]],
      imgUrl: ['']
    });
  }

  ngOnInit(): void {
    const navigation = this.router.getCurrentNavigation();
    if (navigation && navigation.extras.state && navigation.extras.state['offer']) {
        const offer = navigation.extras.state['offer'];
        this.updateForm.patchValue(offer);
        this.offreTransportId = offer.id; // Assurez-vous d'assigner l'ID ici
        console.log('Offer data received:', offer);
    } else {
        console.error('No offer data received');
    }
  }
  

  loadOffreTransport(id: number): void {
    this.offreTransportService.getApprovedOffreById(id).subscribe(
      data => {
        this.offer = data;
        this.updateForm.patchValue(data); // Remplir le formulaire avec les données
      },
      error => {
        console.error('Error loading offer:', error);
      }
    );
  }

  onSubmit(): void {
    if (this.updateForm.valid) {
      const token = this.authService.getToken();
      const decodedToken = token ? this.authService.decodeToken(token) : null;
  
      const reservation: ReservationDto = {
        id: 0,
        dateReservation: new Date(), // Utilisez un objet Date
        employeId: decodedToken ? decodedToken.employeId : 0,
        offreId: this.offreTransportId, // Assurez-vous que cette propriété est correctement définie
        montant: this.updateForm.value.prix,
        transactionId: null,
        nombrePlaces: this.updateForm.value.nombrePlaces,
        pointDepart: '',
        destination: ''
      };
  
      this.reservationService.reserve(reservation).subscribe(
        (reservationDto) => {
          console.log('Reservation created successfully:', reservationDto);
          this.router.navigate(['/reservations/success']);
        },
        (reservationError) => {
          console.error('Error creating reservation:', reservationError);
        }
      );
    }
  }
  
}
