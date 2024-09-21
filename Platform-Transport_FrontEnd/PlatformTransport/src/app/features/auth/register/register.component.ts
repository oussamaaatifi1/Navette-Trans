import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService
  ) {
    // Initialize the form group with validators
    this.registerForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.registerForm.valid) {
      const { nom, prenom, email, password } = this.registerForm.value;
      const registerRequest = {
        nom,
        prenom,
        email,
        password
      };

      this.authService.registerEmploye(registerRequest).subscribe({
        next: (response) => {
          console.log('Registration successful:', response);
          this.router.navigate(['/login']); // Redirect after successful registration
        },
        error: (error) => {
          console.error('Registration failed:', error);
          // Optionally, you can show a user-friendly error message here
        }
      });
    } else {
      console.error('Form is invalid');
      // Optionally, you can show validation errors to the user here
    }
  }
}
