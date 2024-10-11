import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';
import { Router } from '@angular/router';
import { RegisterRequest } from '../../../core/models/RegisterRequest';

@Component({
  selector: 'app-register-popup',
  templateUrl: './register-employeur.component.html',
  styleUrls: ['./register-employeur.component.css']
})
export class RegisterPopupComponent {
  @Input() isPopupVisible: boolean = false; // Control the popup visibility
  @Output() close = new EventEmitter<void>(); // Event to close the popup

  registerRequest: RegisterRequest = {
    nom: '',
    prenom: '',
    email: '',
    password: ''
  };

  errorMessage: string | null = null;

  constructor(private authService: AuthService, private router: Router) {}

  // Close the popup
  closePopup() {
    this.isPopupVisible = false;
    this.close.emit(); // Emit close event to parent
  }

  // Submit the registration form
  onSubmit(): void {
    this.authService.registerEmployeur(this.registerRequest).subscribe({
      next: (response) => {
        console.log('Registration successful:', response);
        this.closePopup(); // Close popup after successful registration
      },
      error: (error) => {
        console.error('Registration failed:', error);
        this.errorMessage = 'Registration failed. Please try again.'; // Show error message
      }
    });
  }
}
