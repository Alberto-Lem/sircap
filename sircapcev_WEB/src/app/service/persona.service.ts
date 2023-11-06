// persona.service.ts
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Persona } from '../interface/persona.interface';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {
  personaURL = environment.apiUrl + '/personas';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<Persona[]> {
    return this.httpClient.get<Persona[]>(this.personaURL);
  }

  public detail(id: string): Observable<Persona> {
    return this.httpClient.get<Persona>(this.personaURL + `/${id}`);
  }
  
  public findByNip(nip: string): Observable<Persona | null> {
    return this.httpClient.get<Persona | null>(this.personaURL + `/findByNip/${nip}`);
  }

  public create(persona: Persona): Observable<any> {
    return this.httpClient.post<any>(this.personaURL, persona);
  }

  public update(id: string, persona: Persona): Observable<any> {
    return this.httpClient.put<any>(this.personaURL + `/${id}`, persona);
  }

  public delete(id: string): Observable<any> {
    return this.httpClient.delete<any>(this.personaURL + `/${id}`);
  }
}
