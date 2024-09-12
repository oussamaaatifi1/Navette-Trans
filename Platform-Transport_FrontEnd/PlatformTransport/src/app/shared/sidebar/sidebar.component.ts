import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from 'src/app/core/models/enum/Role';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  currentSection: string = 'dashboard';

  
  // utilisateur: Utilisateurs[] = [];
nom: any;
email: any;
role: Role | null | undefined;



  constructor(
    private router: Router,
    private authService: AuthService,
    // private userService: UtilisateurService // Inject the user service
  ) {}

  ngOnInit(): void {
    this.getEmail();
    // this.nom = this.authService.getNom();
    // this.email = this.authService.getEmail();
    this.role = this.authService.getRole();
  }

  getEmail(): void {
    // this.authService.getEmail()
  }
  

  
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isDropdownOpen = false;

  loadContent(section: string) {
    this.currentSection = section;
    this.router.navigate([`/${section}`]);
  }

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  isAdmin(): boolean {
    return this.role?.toString() === Role[Role.ADMIN];
  }
  isEmploye(): boolean {
    return this.role?.toString() === Role[Role.EMPLOYE];
  }
  isEmployeur(): boolean {
    return this.role?.toString() === Role[Role.EMPLOYEUR];
  }
  isParticulier(): boolean {
    return this.role?.toString() === Role[Role.PARTICULIER];
  }

  addPannes(): void {
    this.router.navigate(['/pannes/add']);
  }
}
