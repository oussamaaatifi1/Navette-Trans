// src/app/services/rapport.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Rapport } from '../../core/models/Rapport';

@Injectable({
  providedIn: 'root'
})
export class RapportService {
  private baseUrl = 'http://localhost:8080/api/rapports'; // Your API endpoint

  constructor(private http: HttpClient) {}

  getAllRapports(): Observable<Rapport[]> {
    return this.http.get<Rapport[]>(this.baseUrl);
  }

  createRapport(rapport: Rapport): Observable<Rapport> {
    return this.http.post<Rapport>(this.baseUrl, rapport);
  }
}
