import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { ReservationService } from 'src/app/core/services/reservation.service';
import { ReservationDto } from 'src/app/core/models/ReservationDto'; // Make sure to import your DTO correctly
import { EmployeServiceService } from 'src/app/core/services/employe-service.service';

@Component({
  selector: 'app-historique',
  templateUrl: './historique.component.html',
  styleUrls: ['./historique.component.css']
})
export class HistoriqueComponent implements OnInit {
  reservations: ReservationDto[] = [];
  employeNom: string | null = null;
  employePrenom: string | null = null;

  constructor(
    private reservationService: ReservationService,
    private authService: AuthService,
    private employeService: EmployeServiceService
  ) {}

  ngOnInit() {
    const employeIdString = this.authService.getUserId();
    const employeId = Number(employeIdString);

    if (employeId) {
      const parsedEmployeId = Number(employeId); // Convert the string to a number
      this.getEmployeeName(parsedEmployeId); // Fetch employee name based on employeId
      // Fetch reservations and assign to the reservations array
      this.reservationService.getReservationsByEmployeId(employeId)
        .subscribe({
          next: (reservations) => {
            this.reservations = reservations;
          },
          error: (error) => {
            console.error('Error fetching reservations', error);
          }
        });
    }
  }

  getEmployeeName(employeId: number): void {
    this.employeService.getEmployeById(employeId).subscribe({
      next: (employe) => {
        this.employeNom = employe.nom ?? null;
        this.employePrenom = employe.prenom ?? null;
      },
      error: (err) => {
        console.error('Error fetching employee details', err);
      }
    });
  }

  // onDeleteReservation(id: number): void {
  //   if (confirm('Êtes-vous sûr de vouloir annuler cette réservation?')) {
  //     this.reservationService.deleteReservation(id).subscribe({
  //       next: () => {
  //         // Filter out the deleted reservation from the list
  //         this.reservations = this.reservations.filter(reservation => reservation.id !== id);
  //         alert('Réservation annulée avec succès.');
  //       },
  //       error: (error) => {
  //         console.error('Erreur lors de l\'annulation de la réservation', error);
  //         alert('Une erreur est survenue lors de l\'annulation de la réservation. Veuillez réessayer.');
  //       }
  //     });
  //   }
  // }
}
