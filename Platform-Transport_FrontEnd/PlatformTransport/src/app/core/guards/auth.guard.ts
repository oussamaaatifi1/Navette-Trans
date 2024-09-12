import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Role } from '../models/enum/Role';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    // Check if the user is logged in
    if (this.authService.isLoggedIn()) {
      const currentRole = this.authService.getRole(); // Retrieve the current role from AuthService
      const expectedRole = route.data['role'] as Role; // Retrieve the expected role from the route data

      // Check if the user's role matches the expected role
      if (currentRole === expectedRole) {
        return true;
      } else {
        // Redirect to a 'not authorized' page if the role doesn't match
        this.router.navigate(['/not-authorized']);
        return false;
      }
    } else {
      // If the user is not logged in, redirect to the login page
      this.router.navigate(['/login']);
      return false;
    }
  }
}
