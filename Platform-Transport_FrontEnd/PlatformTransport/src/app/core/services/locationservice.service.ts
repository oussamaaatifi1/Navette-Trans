import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CityLocation } from '../models/Locations';

@Injectable({
  providedIn: 'root'
})
export class LocationserviceService {

  private locationsUrl = 'assets/locations.json'; // Path to your JSON file

  constructor(private http: HttpClient) {}

  getLocations(): Observable<CityLocation[]> {
    return this.http.get<CityLocation[]>(this.locationsUrl);
  }
}
