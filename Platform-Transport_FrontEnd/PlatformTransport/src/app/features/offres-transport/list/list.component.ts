import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { OffreTransport } from 'src/app/core/models/OffreTransport';
import { OffreTransportService } from 'src/app/core/services/offre-transport.service';
import { AuthService } from 'src/app/core/services/auth.service'; 
import { CreateComponent } from '../create/create.component';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListOffreComponent implements OnInit {

  offreTransport: OffreTransport[] = [];

  constructor(
    private offreTransportService: OffreTransportService,
    private authService: AuthService,
    private router: Router,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getOffresTransports();
  }

  getOffresTransports(): void {
    const token = this.authService.getToken();
  
    if (token) {
      this.offreTransportService.getOffreTransportByEmployeur().subscribe(
        data => {
          this.offreTransport = data;
        },
        error => {
          console.error('Error fetching offers:', error);
        }
      );
    } else {
      console.error('Token is null or invalid');
    }
  }

  updateOffreTransport(id?: number): void {
    if (id !== undefined) {
      this.router.navigate(['/update', id]);
    } else {
      console.error('Offer ID is undefined');
    }
  }

  openCreateDialog(): void {
    const dialogRef = this.dialog.open(CreateComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe((createdOffer: OffreTransport) => {
      if (createdOffer) {
        this.offreTransport.push(createdOffer);
      }
    });
  }

  deleteOffreTransport(id: number): void {
    if (confirm('Are you sure you want to delete this offer?')) {
      this.offreTransportService.deleteOffreTransport(id).subscribe(
        () => this.getOffresTransports(),
        error => console.error('Error deleting offer:', error)
      );
    }
  }
}
