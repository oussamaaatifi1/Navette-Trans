import { Component, OnInit } from '@angular/core';
import { EmployeServiceService } from 'src/app/core/services/employe-service.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { Employe } from 'src/app/core/models/Employe'; // Import the Employe model

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  employe: Employe = {} as Employe; // Initialize as an empty Employe object
  isModalOpen = false;

  constructor(
    private employeService: EmployeServiceService,  // Employee service for fetching employee data
    private authService: AuthService                // Auth service for getting authenticated user info
  ) {}

  ngOnInit(): void {
    this.loadConnectedEmploye();  // Load the currently authenticated employee data
  }

  // Fetch the connected employee's data
  loadConnectedEmploye() {
    const employeInfo = this.authService.getEmployeInfo();  // Get employee info from AuthService
    if (employeInfo && employeInfo.userId) {
      // Fetch the full employee details using the service method based on the user's ID
      this.employeService.getEmployeById(employeInfo.userId).subscribe(
        (employe: Employe) => {
          console.log('Connected employee data fetched:', employe);
          this.employe = employe;  // Update the employee data with the fetched data
        },
        (error) => {
          console.error('Error fetching employee data:', error);
        }
      );
    } else {
      console.error('Employee info is not available.');
    }
  }

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }

  saveChanges() {
    if (!this.employe.id) {
      console.error('Employee ID is undefined. Cannot update employee.');
      return;  // Prevent further execution if employee ID is not available
    }

    // Update employee data using the employee service
    this.employeService.updateEmploye(this.employe.id, this.employe).subscribe(
      (response: Employe) => {
        console.log('Employee updated successfully:', response);
        this.employe = response;  // Update the employee data after a successful update
        this.closeModal();  // Close the modal after saving changes
      },
      (error) => {
        console.error('Error updating employee:', error);
      }
    );
  }
}
