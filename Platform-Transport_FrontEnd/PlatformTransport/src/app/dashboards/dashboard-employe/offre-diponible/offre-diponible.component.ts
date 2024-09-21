import { Component, OnInit } from '@angular/core';
import { OffreTransport } from 'src/app/core/models/OffreTransport';
import { OffreTransportService } from 'src/app/core/services/offre-transport.service';

@Component({
  selector: 'app-offre-diponible',
  templateUrl: './offre-diponible.component.html',
  styleUrls: ['./offre-diponible.component.css']
})
export class OffreDiponibleComponent implements OnInit {

  offretransport: OffreTransport[] = [];
  searchTerm: string = '';
  selectedFilter: string = 'all';

  constructor(private offreService: OffreTransportService) {}

  ngOnInit(): void {
    this.loadApprovedOffreTransports(); 
  }

  loadApprovedOffreTransports(): void {
    this.offreService.getApprovedOffreTransports().subscribe(
      (data: OffreTransport[]) => {
        this.offretransport = data;
      },
      (error) => {
        console.error('Error loading approved offers:', error);
      }
    );
  }

  get filteredOffers(): OffreTransport[] {
    return this.offretransport.filter(offer => {
      const matchesSearchTerm = offer.pointDepart.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
                                offer.destination.toLowerCase().includes(this.searchTerm.toLowerCase());
      const matchesFilter = this.selectedFilter === 'all' || offer.typeOffreTransport === this.selectedFilter;
      return matchesSearchTerm && matchesFilter;
    });
  }

  reserve(offer: OffreTransport): void {
    console.log('Reserving offer:', offer);
  }
}
