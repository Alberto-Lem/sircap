import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenService } from '../service/token.service';

const AUTH = 'Authorization';
const BEARER = 'Bearer '; //El espacion es muy importante
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let intReq = request;
    const token = this.tokenService.getToken();
    if (token !== null) {
      intReq = request.clone({ headers: request.headers.set(AUTH, BEARER + token)});
      
    }
    return next.handle(intReq);
  }
}