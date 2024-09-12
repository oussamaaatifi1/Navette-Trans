import { Component, OnInit } from '@angular/core';
import { Employe } from 'src/app/core/models/Employe';
import { EmployeServiceService } from 'src/app/core/services/employe-service.service';

@Component({
  selector: 'app-employes',
  templateUrl: './employes.component.html',
  styleUrls: ['./employes.component.css']
})
export class EmployesComponent implements OnInit {

  employe: Employe[] = [];
  selectedEmploye: Employe | null = null;

  constructor(private employesService: EmployeServiceService) { }

  ngOnInit(): void {
    this.getAllEmployes();
  }

  getAllEmployes(): void {
    this.employesService.getAllEmployes().subscribe(data => {
      this.employe = data;
    });
  }

  updateEmploye(id: number): void {
    if (this.selectedEmploye) {
      this.employesService.updateEmploye(id, this.selectedEmploye).subscribe(
        (response) => {
          console.log('Employee updated successfully:', response);
          this.getAllEmployes(); // Refresh the list
        },
        (error) => {
          console.error('Error updating employee:', error);
        }
      );
    }
  }

  deleteEmploye(id: number): void {
    this.employesService.deleteEmploye(id).subscribe(
      () => {
        console.log('Employee deleted successfully');
        this.getAllEmployes(); // Refresh the list
      },
      (error) => {
        console.error('Error deleting employee:', error);
      }
    );
  }

  selectEmploye(employe: Employe): void {
    this.selectedEmploye = { ...employe };
  }
}
