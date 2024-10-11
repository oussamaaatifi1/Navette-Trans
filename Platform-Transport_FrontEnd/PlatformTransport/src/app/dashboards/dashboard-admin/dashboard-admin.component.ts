import { Component, OnInit } from '@angular/core';
import { AdministrateurServiceService } from 'src/app/core/services/administrateur-service.service';
import { ReservationService } from 'src/app/core/services/reservation.service';

@Component({
  selector: 'app-dashboard-admin',
  templateUrl: './dashboard-admin.component.html',
  styleUrls: ['./dashboard-admin.component.css']
})
export class DashboardAdminComponent implements OnInit {
  administrateurCount: number | undefined;
  reservationCount: number | undefined;
  employeMontants: any[] = [];

  constructor(private administrateurService: AdministrateurServiceService,
    private reservationSer : ReservationService
  ) { }

  ngOnInit(): void {
    this.getAllMontant();
    this.loadAdministrateurCount();
    this.loadReservationCount();
  }

  loadAdministrateurCount(): void {
    this.administrateurService.getAdministrateurCount().subscribe(
      (count: number) => {
        this.administrateurCount = count;
      },
      (error) => {
        console.error('Error fetching administrator count', error);
      }
    );
  }
  loadReservationCount(): void {
    this.reservationSer.countTotalReservations().subscribe(
      (count: number) => {
        this.reservationCount = count;
      },
      (error) => {
        console.error('Error fetching administrator count', error);
      }
    );
  }
  getAllMontant()  :void {
    this.reservationSer.getMontantSumByEmploye().subscribe(
      (data) => {
        this.employeMontants = data;
      },
      (error) => {
        console.error('Error fetching montant data', error);
      }
    );
  }
}
