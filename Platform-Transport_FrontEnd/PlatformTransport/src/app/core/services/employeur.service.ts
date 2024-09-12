import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Employeur } from '../models/Emloyeur';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmployeurService {

  private BaseUrl = "http://localhost:8088/api/employeur";

  constructor(private httpclient : HttpClient) { }

  getAllEmployeur() : Observable<Employeur[]> {
    return this.httpclient.get<Employeur[]>(`${this.BaseUrl}/all`);
  }
  updateEmployeur(id: number, employeur: Employeur): Observable<Employeur> {
    return this.httpclient.put<Employeur>(`${this.BaseUrl}/${id}`, employeur);
  }

  deleteEmployeur(id: number): Observable<void> {
    return this.httpclient.delete<void>(`${this.BaseUrl}/${id}`);
  }
}
