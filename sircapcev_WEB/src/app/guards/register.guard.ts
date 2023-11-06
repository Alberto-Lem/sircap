import { Injectable } from '@angular/core';
import { TokenService } from '../service/token.service';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RegisterGuard {
  realRol!: string;
  constructor(
    private tokenService: TokenService,
    private router: Router
  ) {}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRoles = route.data['expectedRoles'];
    this.realRol = this.tokenService.isAdmin() ? 'admin' : 'user';
    if (!this.tokenService.isLogged() || expectedRoles.indexOf(this.realRol) < 0) {
      this.router.navigate(['']);
      return false;
    }
    return true;
  }
}