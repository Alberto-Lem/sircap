import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { CreateUserDto } from '../interface/create-user-dto';
import { JwtTokenDto } from "../interface/jwt-token-dto";
import { LoginUserDto } from "../interface/login-user-dto";
import { environment } from 'src/environments/environment.prod';
import { UpdateProfile } from '../interface/UpdateProfile.interface';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    authURL = environment.apiUrl + '/auth/';
    constructor(private httpClient: HttpClient) { }

    public login(dto:LoginUserDto): Observable<JwtTokenDto> {
        return this.httpClient.post<JwtTokenDto>(this.authURL + 'login', dto);
    }
    public register(dto:CreateUserDto): Observable<any> {
        return this.httpClient.post<any>(this.authURL + 'create-user', dto);
    }

    public changePassword(dto:UpdateProfile): Observable<any> {
        return this.httpClient.post<any>(this.authURL + 'change-password', dto);
    }
}