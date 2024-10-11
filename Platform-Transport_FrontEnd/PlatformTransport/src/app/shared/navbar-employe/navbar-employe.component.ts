import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employe } from 'src/app/core/models/Employe';
import { Role } from 'src/app/core/models/enum/Role';
import { Reservation } from 'src/app/core/models/Reservation';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-navbar-employe',
  templateUrl: './navbar-employe.component.html',
  styleUrls: ['./navbar-employe.component.css']
})
export class NavbarEmployeComponent implements OnInit {

  role: Role | null | undefined;
  nom: any;
  email: any;
  prenom: any;

  reservation : Reservation[] = [];
  
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    const employeInfo = this.authService.getEmployeInfo();
    if (employeInfo) {
      this.email = employeInfo.email;
      this.nom = employeInfo.nom;
      this.role = employeInfo.role;
    } else {
      console.error('Failed to load employee information');
    }
  }

  isEmploye(): boolean {
    return this.role?.toString() === Role[Role.EMPLOYE];
  }

  isDropdownOpen = false;
  toggleDropdown(): void {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
