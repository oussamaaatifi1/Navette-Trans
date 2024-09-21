import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { OffreTransport } from '../models/OffreTransport'; // Adjust the path as necessary

@Injectable({
  providedIn: 'root'
})
export class OffreTransportService {
  private baseUrl = 'http://localhost:8088/api/offres-transport';

  constructor(private http: HttpClient) {}

  getOffreTransportById(id: number): Observable<OffreTransport> {
    return this.http.get<OffreTransport>(`${this.baseUrl}/${id}`)
      .pipe(catchError(this.handleError));
  }

  getAllOffresTransports(): Observable<OffreTransport[]> {
    return this.http.get<OffreTransport[]>(`${this.baseUrl}/all`)
      .pipe(catchError(this.handleError));
  }

  createOffreTransport(offreTransport: OffreTransport, token: string): Observable<OffreTransport> {
    const headers = new HttpHeaders().set('Authorization', token);
    return this.http.post<OffreTransport>(`${this.baseUrl}/add`, offreTransport, { headers })
      .pipe(catchError(this.handleError));
  }

  updateOffreTransport(id: number, offreTransport: OffreTransport): Observable<OffreTransport> {
    return this.http.put<OffreTransport>(`${this.baseUrl}/${id}`, offreTransport)
      .pipe(catchError(this.handleError));
  }

  deleteOffreTransport(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`)
      .pipe(catchError(this.handleError));
  }

  getApprovedOffreTransports(): Observable<OffreTransport[]> {
    return this.http.get<OffreTransport[]>(`${this.baseUrl}/approved`)
      .pipe(catchError(this.handleError));
  }

  getOffreTransportByEmployeur(token: string): Observable<OffreTransport[]> {
    const headers = new HttpHeaders().set('Authorization', token);
    return this.http.get<OffreTransport[]>(`${this.baseUrl}/offreByEmployeur`, { headers })
      .pipe(catchError(this.handleError));
  }

  getRejectedOffreTransports(): Observable<OffreTransport[]> {
    return this.http.get<OffreTransport[]>(`${this.baseUrl}/rejected`)
      .pipe(catchError(this.handleError));
  }

  approveOffre(id: number): Observable<OffreTransport> {
    return this.http.put<OffreTransport>(`${this.baseUrl}/${id}/approve`, {})
      .pipe(catchError(this.handleError));
  }

  rejectOffre(id: number): Observable<OffreTransport> {
    return this.http.put<OffreTransport>(`${this.baseUrl}/${id}/reject`, {})
      .pipe(catchError(this.handleError));
  }

  private handleError(error: any) {
    console.error('An error occurred:', error);
    return throwError(() => new Error('Something went wrong; please try again later.'));
  }
}
