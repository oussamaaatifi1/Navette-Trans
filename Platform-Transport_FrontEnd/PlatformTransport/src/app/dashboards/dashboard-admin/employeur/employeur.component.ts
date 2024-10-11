import { Component, OnInit } from '@angular/core';
import { EmployeurService } from 'src/app/core/services/employeur.service';
import { Employeur } from '../../../core/models/Emloyeur';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { RegisterRequest } from 'src/app/core/models/RegisterRequest';

@Component({
  selector: 'app-employeurs',
  templateUrl: './employeur.component.html',
  styleUrls: ['./employeur.component.css']
})
export class EmployeursComponent implements OnInit {
  employeurs: Employeur[] = [];
  selectedEmployeur: Employeur | null = null;

  // New properties for registration
  nom: string = '';
  prenom: string = '';
  email: string = '';
  password: string = '';

  // New property for managing modal visibility
  isModalOpen: boolean = false;

  constructor(
    private employeurService: EmployeurService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getAllEmployeurs();
  }

  getAllEmployeurs(): void {
    this.employeurService.getAllEmployeur().subscribe(
      (data) => {
        this.employeurs = data;
      },
      (error) => {
        console.error('Error fetching employeurs:', error);
      }
    );
  }

  // Method to register an employer
  registerEmployeur(): void {
    const request: RegisterRequest = {
      nom: this.nom,
      prenom: this.prenom,
      email: this.email,
      password: this.password,
    };
  
    this.authService.registerEmployeur(request).subscribe(
      (response) => {
        console.log('Employer registered successfully:', response);
        this.getAllEmployeurs(); // Refresh the list (optional)
        this.resetForm(); // Reset the form (optional)
        this.closeModal(); // Close the modal (optional)
        
        // Navigate to the listemployeur page
        this.router.navigate(['/listemployeur']);
      },
      (error) => {
        console.error('Error registering employer:', error);
      }
    );
  }
  

  resetForm(): void {
    this.nom = '';
    this.prenom = '';
    this.email = '';
    this.password = '';
    this.selectedEmployeur = null; // Reset selected employeur
  }

  // Method to handle editing of an employer
  editEmployeur(employeur: Employeur): void {
    this.selectedEmployeur = employeur; // Set the selected employer
    this.nom = employeur.nom;
    this.prenom = employeur.prenom;
    this.email = employeur.email;
    this.isModalOpen = true; // Open the modal
  }

  updateEmployeur(): void {
    if (this.selectedEmployeur) {
      // Create an object with updated values
      const updatedEmployeur: Employeur = {
        ...this.selectedEmployeur,
        nom: this.nom,
        prenom: this.prenom,
        email: this.email,
        // Password should not be updated unless explicitly changed, handle accordingly
      };

      this.employeurService.updateEmployeur(updatedEmployeur.id, updatedEmployeur).subscribe(
        (response) => {
          console.log('Employeur updated successfully:', response);
          this.getAllEmployeurs(); // Refresh the list
          this.closeModal(); // Close the modal
        },
        (error) => {
          console.error('Error updating employeur:', error);
        }
      );
    }
  }

  deleteEmployeur(id: number): void {
    this.employeurService.deleteEmployeur(id).subscribe(
      () => {
        console.log('Employeur deleted successfully');
        this.getAllEmployeurs();
      },
      (error) => {
        console.error('Error deleting employeur:', error);
      }
    );
  }

  // Methods to open and close the modal
  openModal(): void {
    this.resetForm(); // Reset form when opening modal
    this.isModalOpen = true;
  }

  closeModal(): void {
    this.isModalOpen = false;
    this.resetForm(); // Reset form when closing modal
  }
}
