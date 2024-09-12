import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { OffreTransport } from '../models/OffreTransport';
import { Role } from '../models/enum/Role';

@Injectable({
  providedIn: 'root'
})
export class OffreTransportService {

  private BaseURL = "http://localhost:8088/api/offres-transport";

  constructor(private httpclient: HttpClient) { }

  getOffresTransports(): Observable<OffreTransport[]> {
    return this.httpclient.get<OffreTransport[]>(`${this.BaseURL}/all`).pipe(
      catchError(this.handleError)
    );
  }
  
  createOffreTransport(offreTransport: OffreTransport): Observable<OffreTransport> {
    return this.httpclient.post<OffreTransport>(`${this.BaseURL}/add`, offreTransport).pipe(
      catchError(this.handleError)
    );
  }
  
  getOffreTransportById(id: number): Observable<OffreTransport> {
    return this.httpclient.get<OffreTransport>(`${this.BaseURL}/${id}`).pipe(
      catchError(this.handleError)
    );
  }
  
  updateOffreTransport(id: number, offreTransport: OffreTransport): Observable<OffreTransport> {
    return this.httpclient.put<OffreTransport>(`${this.BaseURL}/${id}`, offreTransport).pipe(
      catchError(this.handleError)
    );
    
  }
  
  deleteOffreTransport(id: number): Observable<any> {
    return this.httpclient.delete<any>(`${this.BaseURL}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  approveOffreTransport(id: number): Observable<OffreTransport> {
    return this.httpclient.put<OffreTransport>(`${this.BaseURL}/${id}/approve`, {}).pipe(
      catchError(this.handleError)
    );
  }

  rejectOffreTransport(id: number): Observable<OffreTransport> {
    return this.httpclient.put<OffreTransport>(`${this.BaseURL}/${id}/reject`, {}).pipe(
      catchError(this.handleError)
    );
  }
  
  // New methods
  getApprovedOffreTransports(): Observable<OffreTransport[]> {
    return this.httpclient.get<OffreTransport[]>(`${this.BaseURL}/approved`).pipe(
      catchError(this.handleError)
    );
  }

  getRejectedOffreTransports(): Observable<OffreTransport[]> {
    return this.httpclient.get<OffreTransport[]>(`${this.BaseURL}/rejected`).pipe(
      catchError(this.handleError)
    );
  }

  
  private handleError(error: any): Observable<never> {
    console.error('An error occurred:', error);
    return throwError(error);
  }
}
