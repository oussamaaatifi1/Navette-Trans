import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employe } from 'src/app/core/models/Employe';
import { EmployeServiceService } from 'src/app/core/services/employe-service.service';

@Component({
  selector: 'app-employe',
  templateUrl: './employe.component.html',
  styleUrls: ['./employe.component.css']
})
export class EmployeComponent implements OnInit{

  constructor(private employeservice : EmployeServiceService,
    private router : Router){}
  
  employe : Employe[] = [];
  

  ngOnInit(): void {
    this.getAllEmployes();
  }
  getAllEmployes() : void {
    this.employeservice.getAllEmployes().subscribe(data =>{
      this.employe = data;
    })
  }

}
