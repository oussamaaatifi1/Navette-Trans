import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LocationserviceService } from 'src/app/core/services/locationservice.service';
import { TypeOffreTransport } from 'src/app/core/models/enum/TypeOffreTransport';
import { OffreTransport } from 'src/app/core/models/OffreTransport';
import { OffreTransportService } from 'src/app/core/services/offre-transport.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { CityLocation } from 'src/app/core/models/Locations';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  offreForm: FormGroup;
  typeOffreTransportOptions = Object.values(TypeOffreTransport);
  cities: CityLocation[] = []; // Declare cities array

  constructor(
    private fb: FormBuilder,
    private offreTransportService: OffreTransportService,
    private authService: AuthService,
    private locationService: LocationserviceService // Inject the service
  ) {
    this.offreForm = this.fb.group({
      typeOffreTransport: ['', Validators.required],
      pointDepart: ['', Validators.required],
      destination: ['', Validators.required],
      dateOffre: ['', Validators.required],
      nombrePlaces: [0, [Validators.required, Validators.min(1)]],
      prix: [0, [Validators.required, Validators.min(0)]],
      imgUrl: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.locationService.getLocations().subscribe(data => {
      this.cities = data; // Fetch cities
    });
  }

  onSubmit(): void {
    if (this.offreForm.valid) {
      const newOffre: OffreTransport = this.offreForm.value;
      const token = this.authService.getToken();
      if (token) {
        this.offreTransportService.createOffreTransport(newOffre, token).subscribe(
          (response) => {
            console.log('Offre created successfully:', response);
            this.offreForm.reset();
          },
          (error) => {
            console.error('Error creating offer:', error);
          }
        );
      } else {
        console.error('Token is not available');
      }
    }
  }
}
