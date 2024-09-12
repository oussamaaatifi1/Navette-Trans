import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OffreTransport } from 'src/app/core/models/OffreTransport';
import { OffreTransportService } from 'src/app/core/services/offre-transport.service';

@Component({
  selector: 'app-offre-diponible',
  templateUrl: './offre-diponible.component.html',
  styleUrls: ['./offre-diponible.component.css']
})
export class OffreDiponibleComponent implements OnInit {

  offretransport! : OffreTransport[];

  constructor(private router : Router, private offreService : OffreTransportService){}
  ngOnInit(): void {
    this.loadOffresTransports(); 
  }

  approveOffre(offer: OffreTransport): void {
    if (offer.id !== undefined) {
      this.offreService.approveOffreTransport(offer.id).subscribe(
        (updatedOffer: OffreTransport) => {
          console.log('Offer approved:', updatedOffer);
          this.loadOffresTransports(); 
        },
        (error) => {
          console.error('Error approving offer:', error);
        }
      );
    } else {
      console.error('Offer ID is undefined');
    }
  }
  

  loadOffresTransports(): void {
    this.offreService.getOffresTransports().subscribe(
      (data: OffreTransport[]) => {
        this.offretransport = data;
      },
      (error) => {
        console.error('Error loading offers:', error);
      }
    );
  }

}
