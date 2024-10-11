import { Component, OnInit } from '@angular/core';
import { OffreTransport } from 'src/app/core/models/OffreTransport';
import { OffreTransportService } from 'src/app/core/services/offre-transport.service';
import { Router } from '@angular/router';
import { TypeOffreTransport } from 'src/app/core/models/enum/TypeOffreTransport';
import { ReservationDto } from 'src/app/core/models/ReservationDto';
import { AuthService } from 'src/app/core/services/auth.service';
import { ReservationService } from 'src/app/core/services/reservation.service';

@Component({
  selector: 'app-offre-diponible',
  templateUrl: './offre-diponible.component.html',
  styleUrls: ['./offre-diponible.component.css'],
})
export class OffreDiponibleComponent implements OnInit {
  offretransport: OffreTransport[] = [];
  searchTerm: string = '';
  selectedFilter: string = 'all';
  transportTypes = Object.values(TypeOffreTransport);
  selectedOffer?: OffreTransport;
  loading: boolean = false;
  error: string = '';
  departSearchTerm: string = '';
  destinationSearchTerm: string = '';
  errorMessage: string = ''; // Property to hold error messages

  reservation: ReservationDto = {
    id: 0,
    dateReservation: new Date(), // Initialize with current date
    employeId: 0, // To be set when reserving
    offreId: 0, // To be set when reserving
    montant: 0.0,
    transactionId: null,
    nombrePlaces: 1,
    destination: '',
    pointDepart: '',
  };

  constructor(
    private offreService: OffreTransportService,
    private router: Router,
    private authService: AuthService,
    private reservationService: ReservationService
  ) {}

  ngOnInit(): void {
    this.loadApprovedOffreTransports();
  }

  loadApprovedOffreTransports(): void {
    this.loading = true;
    this.offreService.getApprovedOffreTransports().subscribe(
      (data: OffreTransport[]) => {
        this.offretransport = data;
        // Optionally filter here if you want to remove 0 available offers immediately
        this.offretransport = this.offretransport.filter(offer => offer.nombrePlaces > 0);
        this.loading = false;
      },
      (error) => {
        this.loading = false;
        this.error = 'Failed to load offers';
        console.error(error);
      }
    );
  }

  get filteredOffers(): OffreTransport[] {
    return this.offretransport.filter((offer) => {
      const matchesDepart = offer.pointDepart
        .toLowerCase()
        .includes(this.departSearchTerm.toLowerCase());
      const matchesDestination = offer.destination
        .toLowerCase()
        .includes(this.destinationSearchTerm.toLowerCase());
      const matchesFilter =
        this.selectedFilter === 'all' ||
        offer.typeOffreTransport === this.selectedFilter;

      // Ensure offers with 0 available places are not included
      return matchesDepart && matchesDestination && matchesFilter && offer.nombrePlaces > 0;
    });
  }

  onReserveClick(offer: OffreTransport): void {
    this.selectedOffer = offer;

    // Ensure the offer ID is defined before assigning it
    if (offer.id !== undefined) {
      this.reservation.offreId = offer.id; // Set selected offer ID
    } else {
      console.error('Offer ID is not available');
      // Handle the case where the offer ID is not found
      return; // Or however you want to manage this case
    }

    const userId = this.authService.getUserId();

    if (userId) {
      this.reservation.employeId = Number(userId); // Convert to number
    } else {
      console.error('User ID is not available');
      this.reservation.employeId = 0; // or throw an error, or however you want to handle it
    }
  }

  closePopup(): void {
    this.selectedOffer = undefined;
    this.reservation = {
      ...this.reservation,
      dateReservation: new Date(),
      nombrePlaces: 1,
    };
  }

  submitReservation(): void {
    const reservationDate = new Date(this.reservation.dateReservation);
    const currentDate = new Date();

    // Check if a valid offer is selected
    if (this.selectedOffer) {
      this.reservation.montant = this.reservation.nombrePlaces * this.selectedOffer.prix;

      // Check available places
      if (this.selectedOffer.nombrePlaces <= 0) {
        // If no places are available, delete the reservation
        this.reservationService.deleteReservation(this.reservation.id).subscribe(
          (response) => {
            console.log('Reservation deleted due to no available places:', response);
            // Reload the page or provide feedback to the user
            window.location.reload();
          },
          (error) => {
            console.error('Failed to delete reservation:', error);
            // Handle error: Set error message state or display a notification
            this.errorMessage = 'Failed to delete the reservation. Please try again.';
          }
        );
      } else {
        // Proceed to reserve the selected offer
        this.reservationService.reserve(this.reservation).subscribe(
          (response) => {
            console.log('Reservation successful:', response);
            // Reload the page or provide feedback to the user
            window.location.reload();
          },
          (error) => {
            console.error('Reservation failed:', error);
            // Handle error: Set error message state or display a notification
            this.errorMessage = 'Failed to reserve the offer. Please try again.';
          }
        );
      }
    } else {
      console.error('No offer selected for reservation');
      // Display an error message to the user if needed
      this.errorMessage = 'Please select an offer to reserve.';
    }
  }
}
