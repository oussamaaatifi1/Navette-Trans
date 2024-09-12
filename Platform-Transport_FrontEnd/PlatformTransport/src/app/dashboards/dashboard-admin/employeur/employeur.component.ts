import { Component, OnInit } from '@angular/core';
import { EmployeurService } from 'src/app/core/services/employeur.service';
import { Employeur } from '../../../core/models/Emloyeur';

@Component({
  selector: 'app-employeurs',
  templateUrl: './employeur.component.html',
  styleUrls: ['./employeur.component.css']
})
export class EmployeursComponent implements OnInit {

  employeurs: Employeur[] = [];
  selectedEmployeur: Employeur | null = null;

  constructor(private employeurService: EmployeurService) { }

  ngOnInit(): void {
    this.getAllEmployeurs();
  }

  getAllEmployeurs(): void {
    this.employeurService.getAllEmployeur().subscribe(data => {
      this.employeurs = data;
    });
  }

  updateEmployeur(id: number): void {
    if (this.selectedEmployeur) {
      this.employeurService.updateEmployeur(id, this.selectedEmployeur).subscribe(
        (response) => {
          console.log('Employeur updated successfully:', response);
          this.getAllEmployeurs(); // Refresh the list
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
        this.getAllEmployeurs(); // Refresh the list
      },
      (error) => {
        console.error('Error deleting employeur:', error);
      }
    );
  }

  selectEmployeur(employeur: Employeur): void {
    this.selectedEmployeur = { ...employeur }; // Create a copy of the selected employeur for editing
  }
}
