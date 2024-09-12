import { Component, OnInit } from '@angular/core';
import { AdministrateurServiceService } from 'src/app/core/services/administrateur-service.service';

@Component({
  selector: 'app-dashboard-admin',
  templateUrl: './dashboard-admin.component.html',
  styleUrls: ['./dashboard-admin.component.css']
})
export class DashboardAdminComponent implements OnInit {
  administrateurCount: number | undefined;

  constructor(private administrateurService: AdministrateurServiceService) { }

  ngOnInit(): void {
    this.loadAdministrateurCount();
  }

  loadAdministrateurCount(): void {
    this.administrateurService.getAdministrateurCount().subscribe(
      (count: number) => {
        this.administrateurCount = count;
      },
      (error) => {
        console.error('Error fetching administrator count', error);
      }
    );
  }
}
