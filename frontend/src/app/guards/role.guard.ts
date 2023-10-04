import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { AuthService } from 'app/common/services/auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RoleGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot,
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const expectedRoles: string[] = ['ADMINISTRATORS', 'DEPARTMENT_MEMBERS', 'APPROVERS'];
    const userRole = localStorage.getItem('role');

    if (userRole && expectedRoles.includes(userRole)) {
      return true;
    } else {
      // Redirect to an unauthorized or a default page (modify as per your needs)
      this.router.navigate(['/login']);
      return false;
    }
  }
}
