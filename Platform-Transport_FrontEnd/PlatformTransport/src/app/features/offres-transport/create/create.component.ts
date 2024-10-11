import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LocationserviceService } from 'src/app/core/services/locationservice.service';
import { TypeOffreTransport } from 'src/app/core/models/enum/TypeOffreTransport';
import { OffreTransport } from 'src/app/core/models/OffreTransport';
import { OffreTransportService } from 'src/app/core/services/offre-transport.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { CityLocation } from 'src/app/core/models/Locations';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  offreForm: FormGroup;
  typeOffreTransportOptions = Object.values(TypeOffreTransport);
  cities: CityLocation[] = [];

  @Output() createEvent = new EventEmitter<OffreTransport>(); // Emit created offer

  constructor(
    private fb: FormBuilder,
    private offreTransportService: OffreTransportService,
    private authService: AuthService,
    private locationService: LocationserviceService,
    private dialogRef: MatDialogRef<CreateComponent> // Dialog reference for closing
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
    this.loadCities();
  }

  loadCities(): void {
    this.locationService.getLocations().subscribe(
      data => {
        this.cities = data;
      },
      error => {
        console.error('Error fetching locations:', error);
      }
    );
  }

  onSubmit(): void {
    if (this.offreForm.valid) {
      const newOffre: OffreTransport = this.offreForm.value;
      const token = this.authService.getToken();

      if (token) {
        this.offreTransportService.createOffreTransport(newOffre).subscribe(
          (createdOffer: OffreTransport) => {
            console.log('Offre created successfully:', createdOffer);
            this.createEvent.emit(createdOffer); // Emit the new offer to parent
            this.dialogRef.close(createdOffer);  // Close the dialog and return the offer
          },
          (error) => {
            console.error('Error creating offer:', error);
          }
        );
      } else {
        console.error('Token is not available');
      }
    } else {
      console.warn('Form is invalid. Please check the input values.');
    }
  }

  onCancel(): void {
    this.dialogRef.close(); // Allow dialog close without submission
  }
}
