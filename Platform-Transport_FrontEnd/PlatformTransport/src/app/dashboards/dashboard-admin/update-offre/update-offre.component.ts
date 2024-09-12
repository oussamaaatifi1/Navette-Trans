import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OffreStatus } from 'src/app/core/models/enum/OffreStatus';
import { OffreTransport } from 'src/app/core/models/OffreTransport';
import { OffreTransportService } from 'src/app/core/services/offre-transport.service';

@Component({
  selector: 'app-update-offre',
  templateUrl: './update-offre.component.html',
  styleUrls: ['./update-offre.component.css']
})
export class UpdateOffreComponent implements OnInit {
  updateForm: FormGroup;
  offreTransportId!: number;

  // Initialize status options based on the OffreStatus enum
  statusOptions = Object.values(OffreStatus);

  constructor(
    private fb: FormBuilder,
    private offreTransportService: OffreTransportService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.updateForm = this.fb.group({
      typeOffreTransport: ['', Validators.required],
      pointDepart: ['', Validators.required],
      destination: ['', Validators.required],
      dateOffre: ['', Validators.required],
      nombrePlaces: ['', [Validators.required, Validators.min(1)]],
      prix: ['', [Validators.required, Validators.min(0)]],
      imgUrl: [''],
      status: ['', Validators.required] // Ensure status is a required field
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.offreTransportId = +params.get('id')!;
      if (this.offreTransportId) {
        this.loadOffreTransport(this.offreTransportId);
      }
    });
  }

  loadOffreTransport(id: number): void {
    this.offreTransportService.getOffreTransportById(id).subscribe(
      data => {
        this.updateForm.patchValue(data);
      },
      error => {
        console.error('Error loading offer:', error);
      }
    );
  }

  onSubmit(): void {
    if (this.updateForm.valid) {
      const updatedOffre: OffreTransport = this.updateForm.value;
      this.offreTransportService.updateOffreTransport(this.offreTransportId, updatedOffre).subscribe(
        () => {
          this.router.navigate(['/offrebyadmin']);
        },
        error => {
          console.error('Error updating offer:', error);
        }
      );
    }
  }

  approve(): void {
    this.offreTransportService.approveOffreTransport(this.offreTransportId).subscribe(
      () => {
        this.updateForm.get('status')?.setValue(OffreStatus.APPROVED);
        this.router.navigate(['/offrebyadmin']);
      },
      error => {
        console.error('Error approving offer:', error);
      }
    );
  }

  reject(): void {
    this.offreTransportService.rejectOffreTransport(this.offreTransportId).subscribe(
      () => {
        this.updateForm.get('status')?.setValue(OffreStatus.REJECTED);
        this.router.navigate(['/offrebyadmin']);
      },
      error => {
        console.error('Error rejecting offer:', error);
      }
    );
  }
}
