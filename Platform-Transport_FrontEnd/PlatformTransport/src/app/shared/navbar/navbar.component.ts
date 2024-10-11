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
  reservation: Reservation[] = [];
  employe: Employe[] = [];
  isDropdownOpen = false;
  utilisateur: Utilisateur[] = [];

  isEmployesTabActive = true;
  isReservationsTabActive = false;

  constructor(
    private router: Router,
    private authService: AuthService,
    // private reservationService: ReservationService,
    private employeeService: EmployeServiceService
  ) {}

  ngOnInit(): void {
    const employeInfo = this.authService.getEmployeInfo();
    if (employeInfo) {
      this.email = employeInfo.email;
      this.nom = employeInfo.nom;
      this.role = employeInfo.role;
    } else {
      console.error('Failed to load employee information');
    }
    this.getAllEmployes();
    // this.nom = this.authService.getNom();
  }

  toggleDropdown(): void {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  getAllEmployes(): void {
    this.employeeService.getAllEmployes().subscribe((data) => {
      this.utilisateur = data;
    });
  }

  
  showEmployes() {
    this.isEmployesTabActive = true;
    this.isReservationsTabActive = false;
  }

  showReservations() {
    this.isEmployesTabActive = false;
    this.isReservationsTabActive = true;
  }

  isAdmin(): boolean {
    return this.role?.toString() === Role[Role.ADMIN];
  }
  isEmployeur(): boolean {
    return this.role?.toString() === Role[Role.EMPLOYEUR];
  }
}
