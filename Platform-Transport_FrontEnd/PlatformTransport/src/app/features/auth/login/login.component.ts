import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Role } from 'src/app/core/models/enum/Role';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  errorMessage: string | null = null;

  constructor(
    private service: AuthService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  submitForm(form: FormGroup): void {
    if (form.invalid) {
      return;
    }
  
    const { email, password } = form.value;
    this.service.login(email, password).subscribe(
      (response: { token: string }) => {
        this.service.saveToken(response.token);
        const role = this.service.getRole();
        const userId = this.service.getUserId(); // Retrieve userId from localStorage
  
        console.log('User ID:', userId); // Check userId in console
        if (role) {
          const dashboardRoute = `/dashboard/${role}`;
          this.router.navigate([dashboardRoute]);
        } else {
          console.error('Role is null or undefined.');
          this.errorMessage = 'Login failed due to an unknown role.';
        }
      },
      (error: any) => {
        console.error('Login failed', error);
        this.errorMessage = 'Login failed. Please check your email and password.';
      }
    );
  }
  
  
}
