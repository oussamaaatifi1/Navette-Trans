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
      const expectedRoles = route.data['roles'] as Role[]; // Retrieve the expected roles from the route data

      // If no specific roles are required, allow access
      if (!expectedRoles || expectedRoles.length === 0) {
        return true;
      }

      // Check if the user's role is included in the expected roles
      if (currentRole && expectedRoles.includes(currentRole)) {
        return true;
      } else {
        // Redirect to a 'not authorized' page if the role doesn't match or is null
        this.router.navigate(['/not-authorized']);
        return false;
      }
    } else {
      // If the user is not logged in, redirect to the login page
      this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
      return false;
    }
  }

  
}