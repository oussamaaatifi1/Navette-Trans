import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  ngOnInit() {
    // const script = document.createElement('script');
    // script.src = 'https://unpkg.com/flowbite@1.4.1/dist/flowbite.min.js';
    // script.onload = () => {
    // };
    // document.body.appendChild(script);
  }
  title = 'PlatformTransport';
}
