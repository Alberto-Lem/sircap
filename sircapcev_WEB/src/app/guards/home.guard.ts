import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { TokenService } from '../service/token.service';

@Injectable({
  providedIn: 'root'
})
export class HomeGuard {
  constructor(
    private tokenService: TokenService,
    private router: Router
  ) {}
}
