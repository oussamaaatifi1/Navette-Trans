import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employe } from '../models/Employe';

@Injectable({
  providedIn: 'root'
})
export class EmployeServiceService {

  private BaseURL = "http://localhost:8088/api/utilisateurs";

  constructor(private http: HttpClient) { }

  // Get all utilisateurs
  getAllEmployes(): Observable<Employe[]> {
    return this.http.get<Employe[]>(this.BaseURL);
  }

  // Create a new utilisateur
  createEmploye(employe: Employe): Observable<Employe> {
    return this.http.post<Employe>(this.BaseURL, employe);
  }

  // Get utilisateur by ID
  getEmployeById(id: number): Observable<Employe> {
    return this.http.get<Employe>(`${this.BaseURL}/${id}`);
  }

  // Update an existing utilisateur
  updateEmploye(id: number, employe: Employe): Observable<Employe> {
    return this.http.put<Employe>(`${this.BaseURL}/${id}`, employe);
  }

  // Delete an utilisateur by ID
  deleteEmploye(id: number): Observable<void> {
    return this.http.delete<void>(`${this.BaseURL}/${id}`);
  }
}
