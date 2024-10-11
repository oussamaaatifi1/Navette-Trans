import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Reservation } from '../models/Reservation';
import { ReservationDto } from '../models/ReservationDto';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private apiUrl = 'http://localhost:8088/api/reservations';

  constructor(private http: HttpClient) {}

  // Method to create a reservation
  reserve(reservation: ReservationDto): Observable<ReservationDto[]> {
    return this.http.post<ReservationDto[]>(`${this.apiUrl}/create`, reservation)
      .pipe(catchError(this.handleError));
  }
  
  getAllReservations(): Observable<ReservationDto[]> {
    return this.http.get<ReservationDto[]>(`${this.apiUrl}/all`)
      .pipe(catchError(this.handleError));
  }

  // Get reservations by employeur
  getReservationsByEmployeId(employeId: number): Observable<ReservationDto[]> {
    return this.http.get<ReservationDto[]>(`${this.apiUrl}/reservationsByEmploye/${employeId}`)
      .pipe(catchError(this.handleError));
}


countTotalReservations(): Observable<number> {
  return this.http.get<number>(`${this.apiUrl}/count-by-employe`);
}

  // Get a specific reservation by ID
  getReservation(id: number): Observable<Reservation> {
    return this.http.get<Reservation>(`${this.apiUrl}/${id}`)
      .pipe(catchError(this.handleError));
  }

  getMontantSumByEmploye(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/sum-by-employe`);
  }

  // Get all reservations
  getReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(this.apiUrl)
      .pipe(catchError(this.handleError));
  }
  

  // Update a reservation by ID
  updateReservation(id: number, reservation: Reservation): Observable<Reservation> {
    return this.http.put<Reservation>(`${this.apiUrl}/${id}`, reservation)
      .pipe(catchError(this.handleError));
  }

  // Delete a reservation by ID
  deleteReservation(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: any) {
    console.error('An error occurred:', error);
    return throwError(() => new Error('Something went wrong; please try again later.'));
  }
}
