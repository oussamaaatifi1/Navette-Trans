import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { OffreTransport } from '../models/OffreTransport';

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

  createOffreTransport(offreTransport: OffreTransport): Observable<OffreTransport> {
    return this.http.post<OffreTransport>(`${this.baseUrl}/add`, offreTransport)
      .pipe(catchError(this.handleError));
  }

  getAllOffresTransportsByPagination(page: number, limit: number): Observable<OffreTransport[]> {
    return this.http.get<OffreTransport[]>(`${this.baseUrl}/all?page=${page}&limit=${limit}`)
      .pipe(catchError(this.handleError));
  }
  
  updateOffreTransport(id: number, offreTransport: OffreTransport): Observable<OffreTransport> {
    return this.http.put<OffreTransport>(`${this.baseUrl}/${id}`, offreTransport)
      .pipe(catchError(this.handleError));
  }

  deleteOffreTransport(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`)
      .pipe(catchError(this.handleError));
  }

  getApprovedOffreTransports(): Observable<OffreTransport[]> {
    return this.http.get<OffreTransport[]>(`${this.baseUrl}/approved`)
      .pipe(catchError(this.handleError));
  }

  getApprovedOffreById(id: number): Observable<OffreTransport> {
    return this.http.get<OffreTransport>(`${this.baseUrl}/approved/${id}`)
      .pipe(catchError(this.handleError));
  }

  getOffreTransportByEmployeur(): Observable<OffreTransport[]> {
    return this.http.get<OffreTransport[]>(`${this.baseUrl}/offreByEmployeur`).pipe(
      catchError(this.handleError)
    );
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
