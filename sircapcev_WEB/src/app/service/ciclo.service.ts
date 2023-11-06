import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ciclo } from '../interface/ciclo.interface';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class CicloService {

  cicloURL = environment.apiUrl + '/ciclo';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<Ciclo[]> {
    return this.httpClient.get<Ciclo[]>(this.cicloURL);
  }

  public detail(id: string): Observable<Ciclo> {
    return this.httpClient.get<Ciclo>(this.cicloURL + `/${id}`);
  }

  public create(ciclo: Ciclo): Observable<Ciclo> {
    return this.httpClient.post<Ciclo>(this.cicloURL, ciclo);
  }
  public update(id: string, ciclo: Ciclo): Observable<Ciclo> {
    return this.httpClient.put<Ciclo>(`${this.cicloURL}/${id}`, ciclo);
  }

  public delete(id: string): Observable<any> {
    return this.httpClient.delete<any>(`${this.cicloURL}/${id}`);
  }
 }
