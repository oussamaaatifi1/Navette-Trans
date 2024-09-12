import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdministrateurServiceService {
  
  private apiUrl = 'http://localhost:8088/api/administrateurs';

  constructor(private http: HttpClient) { }

  getAdministrateurCount(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/count`);
  }
}
