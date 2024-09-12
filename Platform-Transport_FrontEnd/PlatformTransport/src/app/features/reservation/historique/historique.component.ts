import { Component, OnInit } from '@angular/core';
import { Reservation } from 'src/app/core/models/Reservation';
import { ReservationService } from 'src/app/core/services/reservation.service';

@Component({
  selector: 'app-historique',
  templateUrl: './historique.component.html',
  styleUrls: ['./historique.component.css']
})
export class HistoriqueComponent implements OnInit {
  
  reservations: Reservation[] = [];

  constructor(private reservationService: ReservationService) { }

  ngOnInit(): void {
    this.loadReservations();
  }

  loadReservations(): void {
    this.reservationService.getReservation(1).subscribe(
      (data: Reservation) => this.reservations = [data], // Assuming you only need one reservation
      error => console.error('Error fetching reservation', error)
    );
  }

}
