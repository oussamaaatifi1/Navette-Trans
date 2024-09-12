import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OffreTransport } from 'src/app/core/models/OffreTransport';
import { OffreTransportService } from 'src/app/core/services/offre-transport.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListOffreComponent implements OnInit {

  offreTransport: OffreTransport[] = [];
  filteredOffres: OffreTransport[] = [];
  searchTerm: string = '';

  constructor(private offreTransportService: OffreTransportService, private router: Router) {}

  ngOnInit(): void {
    this.getApprovedOffreTransports();
  }

  getApprovedOffreTransports(): void {
    this.offreTransportService.getApprovedOffreTransports().subscribe(data => {
      this.offreTransport = data;
      this.filteredOffres = data;
    });
  }

  filterOffers(): void {
    if (this.searchTerm) {
      this.filteredOffres = this.offreTransport.filter(offre =>
        offre.pointDepart.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        offre.destination.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    } else {
      this.filteredOffres = this.offreTransport;
    }
  }
}
