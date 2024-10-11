import { Component, OnInit } from '@angular/core';
import { LocationserviceService } from 'src/app/core/services/locationservice.service';
import { CityLocation  } from 'src/app/core/models/Locations';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  cities: CityLocation[] = [];
  departLocation: string = '';
  destinationLocation: string = '';
  pickupDate: string = '';
  numberseats: string = '';

  constructor(private locationService: LocationserviceService) {}

  ngOnInit(): void {
    this.locationService.getLocations().subscribe(data => {
      this.cities = data;
      if (this.cities.length > 0) {
        this.departLocation = this.cities[0].name;
      }
      if (this.cities.length > 1) {
        this.destinationLocation = this.cities[1].name;
      }
    });
  }

  onSubmit(): void {
    console.log('Depart:', this.departLocation);
    console.log('Destination:', this.destinationLocation);
    console.log('Pickup Date:', this.pickupDate);
    console.log('Return Date:', this.numberseats);
  }
}
