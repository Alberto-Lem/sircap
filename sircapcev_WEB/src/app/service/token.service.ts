import { Injectable } from "@angular/core";

const TOKEN_KEY = 'AuthToken';

@Injectable({
    providedIn: 'root'
})
export class TokenService {
    constructor() { }

    isTokenExpired() {
        if (typeof localStorage !== 'undefined') {
            const token = this.getToken();
            if (!token) {
                return true;
            }

            const now = new Date();
            const expirationDate = new Date(token.split('.')[1]);
            return expirationDate < now;
        } else {
            // Manejar el caso en el que localStorage no esté disponible (en el servidor)
            return false; // O implementa una lógica específica en este caso
        }
    }

    public setToken(token: string): void {
        if (typeof localStorage !== 'undefined') {
            localStorage.setItem(TOKEN_KEY, token);
        } else {
            // Manejar el caso en el que localStorage no esté disponible (en el servidor)
        }
    }

    public getToken(): string | null {
        if (typeof localStorage !== 'undefined') {
            return localStorage.getItem(TOKEN_KEY);
        } else {
            // Manejar el caso en el que localStorage no esté disponible (en el servidor)
            return null; // O implementa una lógica específica en este caso
        }
    }

    public logOut(): void {
        if (typeof localStorage !== 'undefined') {
            localStorage.removeItem(TOKEN_KEY);
        } else {
            // Manejar el caso en el que localStorage no esté disponible (en el servidor)
        }
    }

    public isLogged(): boolean {
        if (typeof localStorage !== 'undefined') {
            return this.getToken() !== null;
        } else {
            // Manejar el caso en el que localStorage no esté disponible (en el servidor)
            return false; // O implementa una lógica específica en este caso
        }
    }
    public isAdmin(): boolean {
        if (!this.isLogged()) {
            return false;
        }
        const token = this.getToken();
        const payload = token!.split('.')[1];
        const payloadDecoded = atob(payload);
        const values = JSON.parse(payloadDecoded);
        const roles = values.roles;
        if (roles.indexOf('ROLE_ADMIN') < 0) {
            return false;
        }
        return true;
    }

    public getUsername(): string {
        if (!this.isLogged()) {
            return '';
        }
        const token = this.getToken();
        const payload = token!.split('.')[1];
        const payloadDecoded = atob(payload);
        const values = JSON.parse(payloadDecoded);
        return values.sub;
    }
}