import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Asignatura } from '../interface/asignatura.interface';
import { environment } from 'src/environments/environment.prod';

@Injectable({ // Decorador Injectable
  providedIn: 'root' // Disponible de forma global en toda la aplicación
})

export class AsignaturaService {

  asignaturaURL = environment.apiUrl + '/asignatura';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<Asignatura[]> {
    return this.httpClient.get<Asignatura[]>(this.asignaturaURL);
  }

  public detail(id: string): Observable<Asignatura> {
    return this.httpClient.get<Asignatura>(this.asignaturaURL + `/${id}`);
  }

  public create(asignatura: Asignatura): Observable<Asignatura> {
    return this.httpClient.post<Asignatura>(this.asignaturaURL, asignatura);
  }
  public update(id: string, asignatura: Asignatura): Observable<Asignatura> {
    return this.httpClient.put<Asignatura>(`${this.asignaturaURL}/${id}`, asignatura);
  }

  public delete(id: string): Observable<any> {
    return this.httpClient.delete<any>(`${this.asignaturaURL}/${id}`);
  }
}
