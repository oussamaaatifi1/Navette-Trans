import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { OffreTransport } from 'src/app/core/models/OffreTransport';
import { OffreTransportService } from 'src/app/core/services/offre-transport.service';

@Component({
  selector: 'app-managemnt-offre',
  templateUrl: './managemnt-offre.component.html',
  styleUrls: ['./managemnt-offre.component.css']
})
export class ManagemntOffreComponent implements OnInit {

  offretransport: OffreTransport[] = [];

  constructor(
    private offretransportService: OffreTransportService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getOffresTransports();
  }

  getOffresTransports(): void {
    this.offretransportService.getAllOffresTransports().subscribe(
      data => {
        this.offretransport = data;
      },
      error => {
        console.error("Error fetching offers:", error);
      }
    );
  }

  updateOffreTransport(id?: number): void {
    if (id !== undefined) {
      this.router.navigate(['/update', id]);
    } else {
      console.error('Offer ID is undefined');
    }
  }

  deleteOffreTransport(id: number): void {
    if (confirm('Are you sure you want to delete this offer?')) {
      this.offretransportService.deleteOffreTransport(id).subscribe(
        () => this.getOffresTransports(),
        error => console.error('Error deleting offer:', error)
      );  
    }
  }
}
