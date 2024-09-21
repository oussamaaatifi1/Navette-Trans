import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent{
  isLoggedIn: boolean = false;
  nom: string | null = null;
  prenom: string | null = null;
  imgUrl: string | null = null;
  email: string | null = null;
  role: string | null = null;
  userId: string | null = null;

  constructor(private authService: AuthService,
    private router : Router)
  {}

  
}
