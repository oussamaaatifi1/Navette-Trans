import { Component, OnInit } from '@angular/core';
import { Reservation } from 'src/app/core/models/Reservation';
import { ReservationService } from 'src/app/core/services/reservation.service';

@Component({
  selector: 'app-createreserve',
  templateUrl: './createreserve.component.html',
  styleUrls: ['./createreserve.component.css']
})
export class CreatereserveComponent implements OnInit {

  reservations: Reservation[] = [];

  constructor(
    private reservationService: ReservationService
  ) {}

  ngOnInit(): void {
    this.getReservations();
  }

  getReservations(): void {
    this.reservationService.getReservations().subscribe(
      (reservations: Reservation[]) => {
        this.reservations = reservations;
      },
      (error) => {
        console.error('Error fetching reservations:', error);
      }
    );
  }
}
