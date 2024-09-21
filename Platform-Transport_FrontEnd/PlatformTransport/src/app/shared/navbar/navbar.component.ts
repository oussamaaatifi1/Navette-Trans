import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employe } from 'src/app/core/models/Employe';
import { Reservation } from 'src/app/core/models/Reservation';
import { AuthService } from 'src/app/core/services/auth.service';
import { ReservationService } from 'src/app/core/services/reservation.service';
import { EmployeServiceService } from 'src/app/core/services/employe-service.service';
import { Utilisateur } from 'src/app/core/models/utilisateur';
import { Role } from 'src/app/core/models/enum/Role';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  role: Role | null | undefined;
  nom: any;
  email: any;
  prenom: any;

  constructor(
    private router: Router,
    private authService: AuthService,
    // private reservationService: ReservationService,
    private employeeService: EmployeServiceService
  ) {}

  ngOnInit(): void {
    this.getAllEmployes();
    this.role = this.authService.getRole();
    // this.nom = this.authService.getNom();
    this.email = this.authService.getEmail();
    
  }

  // getNom(): void {
  //   this.authService.getNom()
  // }

  
  isDropdownOpen = false;
  utilisateur: Utilisateur[] = [];

  // loadNewEmployees(): void {
  //   this.loadNewEmployees();
  //   this.employeeService.getAllEmployes().subscribe((employees) => {
  //     this.employee = employees;
  //   });
  // }

  toggleDropdown(): void {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  reservations: Reservation[] = [];

  getAllEmployes(): void {
    this.employeeService.getAllEmployes().subscribe((data) => {
      this.utilisateur = data;
    });
  }

  isAdmin(): boolean {
    return this.role?.toString() === Role[Role.ADMIN];
  }
  // loadReservations(): void {
  //   this.reservationService.getReservation(1).subscribe(
  //     (data: Reservation) => this.reservations = [data], // Assuming you only need one reservation
  //     error => console.error('Error fetching reservation', error)
  //   );
  // }
}
