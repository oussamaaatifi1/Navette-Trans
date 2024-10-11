import { Component, OnInit } from '@angular/core';
import { Employe } from 'src/app/core/models/Employe';
import { ReservationDto } from 'src/app/core/models/ReservationDto';
import { AuthService } from 'src/app/core/services/auth.service';
import { EmployeServiceService } from 'src/app/core/services/employe-service.service';
import { ReservationService } from 'src/app/core/services/reservation.service';

@Component({
  selector: 'app-list-reservation-employe',
  templateUrl: './list-reservation-employe.component.html',
  styleUrls: ['./list-reservation-employe.component.css']
})
export class ListReservationEmployeComponent implements OnInit {

  allReservations: ReservationDto[] = []; // To store all reservations
  filteredReservations: ReservationDto[] = []; // To store filtered reservations
  employesMap: { [key: number]: Employe } = {}; // Map to hold employee data
  selectedEmployee: Employe | null = null; // To hold the selected employee details

  constructor(
    private reservationService: ReservationService,
    private authService: AuthService,
    private employeService: EmployeServiceService
  ) {}

  ngOnInit(): void {
    this.loadAllEmployees(); // Load all employees once
    this.loadAllReservations(); // Fetch all reservations once
  }

  // Fetch all employees and store them in a map
  loadAllEmployees(): void {
    this.employeService.getAllEmployes().subscribe({
      next: (employes: Employe[]) => {
        this.employesMap = employes.reduce((map, employe) => {
          map[employe.id] = employe;
          return map;
        }, {} as { [key: number]: Employe });
      },
      error: (err) => {
        console.error('Error fetching employees', err);
      }
    });
  }

  // Fetch all reservations once
  loadAllReservations(): void {
    this.reservationService.getAllReservations().subscribe({
      next: (reservations: ReservationDto[]) => {
        this.allReservations = reservations;
      },
      error: (err) => {
        console.error('Error fetching all reservations', err);
      }
    });
  }

  // Show the employee details and filter reservations
  showEmployeeDetails(employeId: number): void {
    this.selectedEmployee = this.employesMap[employeId] || null;
    if (this.selectedEmployee) {
      // Filter reservations based on the selected employee's ID
      this.filteredReservations = this.allReservations.filter(reservation => reservation.employeId === employeId);
    }
  }

  // Close the modal
  closeModal(): void {
    this.selectedEmployee = null;
    this.filteredReservations = []; // Clear the filtered reservations when the modal is closed
  }

  // Delete a reservation and refresh the filtered list
  deleteReservation(reservationId: number): void {
    this.reservationService.deleteReservation(reservationId).subscribe(() => {
      if (this.selectedEmployee) {
        this.filteredReservations = this.filteredReservations.filter(reservation => reservation.id !== reservationId);
      }
    });
  }
}
