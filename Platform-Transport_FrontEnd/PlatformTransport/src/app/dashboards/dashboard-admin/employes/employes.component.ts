import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
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

  constructor(private employesService: EmployeServiceService,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.getAllEmployes();
  }

  getAllEmployes(): void {
    this.employesService.getAllEmployes().subscribe(data => {
      this.employe = data;
    });
  }

  updateEmploye(id?: number): void {
    if (id !== undefined) {
      this.router.navigate(['/update-emloye', id]);
    } else {
      console.error('Offer ID is undefined');
    }
  }
  deleteEmploye(id: number): void {
    this.employesService.deleteEmploye(id).subscribe(
      () => {
        console.log('Employee deleted successfully');
        this.getAllEmployes();
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
