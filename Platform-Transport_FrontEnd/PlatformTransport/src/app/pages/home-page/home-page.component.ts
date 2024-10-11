import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';

interface CarType {
  name: string;
  icon: string;
  count: number;
}

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  @ViewChild('scrollContainer') scrollContainer!: ElementRef;

  carTypes: CarType[] = [
    { name: 'Sedan', icon: 'https://dreamsrent.dreamstechnologies.com/html/template/assets/img/cars/mp-vehicle-05.svg', count: 15 },
    { name: 'Pickup', icon: 'https://dreamsrent.dreamstechnologies.com/html/template/assets/img/cars/mp-vehicle-04.svg', count: 17 },
    { name: 'Family MPV', icon: 'https://dreamsrent.dreamstechnologies.com/html/template/assets/img/cars/mp-vehicle-05.svg', count: 24 },
    { name: 'Crossover', icon: 'https://dreamsrent.dreamstechnologies.com/html/template/assets/img/cars/mp-vehicle-01.svg', count: 35 },
    { name: 'Sports Coupe', icon: 'https://dreamsrent.dreamstechnologies.com/html/template/assets/img/cars/mp-vehicle-02.svg', count: 45 },
    { name: 'Sedan', icon: 'https://dreamsrent.dreamstechnologies.com/html/template/assets/img/cars/mp-vehicle-03.svg', count: 15 },
    { name: 'Sedan', icon: 'https://dreamsrent.dreamstechnologies.com/html/template/assets/img/cars/mp-vehicle-05.svg', count: 15 },
    { name: 'Pickup', icon: 'https://dreamsrent.dreamstechnologies.com/html/template/assets/img/cars/mp-vehicle-04.svg', count: 17 },
    { name: 'Family MPV', icon: 'https://dreamsrent.dreamstechnologies.com/html/template/assets/img/cars/mp-vehicle-05.svg', count: 24 },
    { name: 'Crossover', icon: 'https://dreamsrent.dreamstechnologies.com/html/template/assets/img/cars/mp-vehicle-01.svg', count: 35 },
    { name: 'Sports Coupe', icon: 'https://dreamsrent.dreamstechnologies.com/html/template/assets/img/cars/mp-vehicle-02.svg', count: 45 },
    { name: 'Sedan', icon: 'https://dreamsrent.dreamstechnologies.com/html/template/assets/img/cars/mp-vehicle-03.svg', count: 15 },
  ];


  // Change this to public
  constructor(public authService: AuthService) {
    this.isLoggedIn = this.authService.isLoggedIn(); // Assuming you have this method
  }

  isLoggedIn!: boolean;

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn(); // Check if the user is logged in
  }

  

  logout(): void {
    this.authService.logout(); // Call logout method from AuthService
    this.isLoggedIn = false; // Update the login status
  }

  scroll(direction: number): void {
    if (this.scrollContainer) {
      this.scrollContainer.nativeElement.scrollBy({ left: direction * 200, behavior: 'smooth' });
    }
  }

  isMenuOpen = false; 

  toggleMenu() {
      this.isMenuOpen = !this.isMenuOpen; // Toggle the menu state
  }

  // New update method added
  updateCarTypes(newCarTypes: CarType[]): void {
    this.carTypes = newCarTypes;
  }
}
