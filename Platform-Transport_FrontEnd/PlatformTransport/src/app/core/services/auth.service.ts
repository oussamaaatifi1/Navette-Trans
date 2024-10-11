import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { Role } from '../models/enum/Role';
import { RegisterRequest } from '../models/RegisterRequest';
import {jwtDecode} from 'jwt-decode'; // Correct import of jwtDecode
import { Jwt } from '../models/Jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8088/auth';
  private email: string | null = null;
  private role: Role | null = null;
  private nom: string | null = null;
  private userId: string | null = null;

  private hasNavigated = false; // Add this line in your AuthService

hasNavigatedBefore(): boolean {
  return this.hasNavigated;
}

setHasNavigated(): void {
  this.hasNavigated = true;
}


  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/authenticate`, { email, password }).pipe(
      tap(response => {
        this.saveToken(response.token);
        console.log('Token stored:', response.token);
      }),
      catchError((error: HttpErrorResponse) => {
        console.error('Login failed:', error);
        return throwError(error);
      })
    );
  }

  getEmail(): string | null {
    if (!this.email) {
      const token = this.getToken();
      if (token) {
        this.email = this.decodeToken(token).sub;
      } else {
        console.error('Token is null, cannot retrieve email');
      }
    }
    return this.email;
  }

  registerParticulier(request: RegisterRequest): Observable<any> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/register/registerParticulier`, request).pipe(
      tap(response => {
        this.saveToken(response.token);
        console.log('Registration successful:', response);
      }),
      catchError((error: HttpErrorResponse) => {
        console.error('Registration failed:', error);
        return throwError(error);
      })
    );
  }

  registerAdmin(request: RegisterRequest): Observable<any> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/register/registerAdmin`, request).pipe(
      tap(response => {
        this.saveToken(response.token);
        console.log('Registration successful:', response);
      }),
      catchError((error: HttpErrorResponse) => {
        console.error('Registration failed:', error);
        return throwError(error);
      })
    );
  }

  registerEmploye(request: RegisterRequest): Observable<any> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/register/registerEmploye`, request).pipe(
      tap(response => {
        this.saveToken(response.token);
        console.log('Registration successful:', response);
      }),
      catchError((error: HttpErrorResponse) => {
        console.error('Registration failed:', error);
        return throwError(error);
      })
    );
  }

  registerEmployeur(request: RegisterRequest): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/register/registerEmployeur`, request).pipe(
      tap(response => {
        console.log('Registration successful:', response);
        // No token or login logic here
      }),
      catchError((error: HttpErrorResponse) => {
        console.error('Registration failed:', error);
        return throwError(error);
      })
    );
  }
  


  saveToken(token: string): void {
    localStorage.setItem('authToken', token);
    this.setUserDataFromToken(token);
  }

  setUserDataFromToken(token: string | null): void {
    if (token) {
      const decodedToken: any = this.decodeToken(token);
      console.log('Decoded Token:', decodedToken);
      this.email = decodedToken.sub;
      this.nom = decodedToken.nom;
      this.role = decodedToken.roles as Role;
      this.userId = decodedToken.userId;
      this.saveUserIdToLocalStorage(this.userId);
    } else {
      console.error('Token is null, cannot set user data');
    }
  }

  decodeToken(token: string): any {
    return jwtDecode(token);
  }

  getEmployeInfo() {
    const token = this.getToken();
    if (token) {
      const decodedToken: any = this.decodeToken(token);
      return {
        email: decodedToken.sub,
        nom: decodedToken.nom,
        userId: decodedToken.userId,
        role: decodedToken.roles as Role,
      };
    } else {
      console.error('Token is null, cannot retrieve employee info');
      return null;
    }
  }

  saveUserIdToLocalStorage(userId: string | null): void {
    if (userId) {
      localStorage.setItem('userId', userId);
    }
  }

  getUserId(): string | null {
    return localStorage.getItem('userId');
  }

  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  getRole(): Role | null {
    if (!this.role) {
      const token = this.getToken();
      if (token) {
        this.role = this.decodeToken(token).roles as Role;
      } else {
        console.error('Token is null, cannot retrieve role');
      }
    }
    return this.role;
  }

  getUserNom(): string | null {
    return localStorage.getItem('userNom'); // You can replace this with an API call or other logic
  }

  getUserPrenom(): string | null {
    return localStorage.getItem('userPrenom'); // You can replace this with an API call or other logic
  }
  
  logout(): void {
    localStorage.removeItem('authToken');
    localStorage.removeItem('userId');
    this.email = null;
    this.role = null;
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
