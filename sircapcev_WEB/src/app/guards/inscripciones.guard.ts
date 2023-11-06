import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { TokenService } from '../service/token.service';

@Injectable({
  providedIn: 'root'
})
export class InscripcionesGuard {
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
