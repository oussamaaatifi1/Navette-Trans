import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {

  
  currentYear: number = new Date().getFullYear();

  submitEmail(email: string) {
    // Implement email submission logic here
    console.log('Email submitted:', email);
  }
}
