import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TypeOffreTransport } from 'src/app/core/models/enum/TypeOffreTransport';
import { OffreTransport } from 'src/app/core/models/OffreTransport';
import { OffreTransportService } from 'src/app/core/services/offre-transport.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  offreForm: FormGroup;
  typeOffreTransportOptions = Object.values(TypeOffreTransport);

  constructor(
    private fb: FormBuilder,
    private offreTransportService: OffreTransportService
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

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.offreForm.valid) {
      const newOffre: OffreTransport = this.offreForm.value;
      this.offreTransportService.createOffreTransport(newOffre).subscribe(
        (response) => {
          console.log('Offre created successfully:', response);
          this.offreForm.reset();
        },
        (error) => {
          console.error('Error creating offer:', error);
        }
      );
    }
  }
}
