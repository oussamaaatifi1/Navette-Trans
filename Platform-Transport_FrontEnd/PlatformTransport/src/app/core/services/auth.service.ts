import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { Role } from '../models/enum/Role';
import { Jwt } from '../models/Jwt';
import { RegisterRequest } from '../models/RegisterRequest';
import {jwtDecode} from 'jwt-decode'; // Ensure correct import

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8088/auth';
  private email: string | null = null;
  private role: Role | null = null;
  private nom: string | null = null;
  private userId: string | null = null;

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
    return this.http.post<{ token: string }>(`${this.apiUrl}/register/registerEmployeur`, request).pipe(
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

  saveToken(token: string): void {
    localStorage.setItem('authToken', token);
    this.setUserDataFromToken(token);
  }

  setUserDataFromToken(token: string): void {
    const decodedToken: any = this.decodeToken(token);
    console.log('Decoded Token:', decodedToken);
    this.email = decodedToken.sub;
    this.nom = decodedToken.nom;
    this.role = decodedToken.roles as Role;
    this.userId = decodedToken.userId;
    this.saveUserIdToLocalStorage(this.userId);
  }

  decodeToken(token: string): any {
    return jwtDecode(token);
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
      }
    }
    return this.role;
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