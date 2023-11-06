import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Planes } from '../planes/planesinterface/planes.interface';
interface ConteoDatos {
  name: string;
  value: number;
}
@Injectable({
  providedIn: 'root'
})
export class PlanesService {
  planesURL = environment.apiUrl + '/planes';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<Planes[]> {
    return this.httpClient.get<Planes[]>(this.planesURL);
  }

  public detail(id: string): Observable<Planes> {
    return this.httpClient.get<Planes>(this.planesURL + `/${id}`);
  }

  public create(planes: Planes): Observable<Planes> {
    return this.httpClient.post<Planes>(this.planesURL, planes);
  }
  public update(id: string, planes: Planes): Observable<Planes> {
    return this.httpClient.put<Planes>(`${this.planesURL}/${id}`, planes);
  }

  public delete(id: string): Observable<any> {
    return this.httpClient.delete<any>(`${this.planesURL}/${id}`);
  }

 
  private abc: ConteoDatos[] = [
    {
      "name": "DG",
      "value": 1000
    },
    {
      "name": "DGA",
      "value": 900
    },
    {
      "name": "SGO",
      "value": 800
    },
    {
      "name": "SGIC",
      "value": 700
    },
    {
      "name": "SGP",
      "value": 600
    },
    {
      "name": "SGAL",
      "value": 500
    },
    {
      "name": "SGAIA",
      "value": 400
    },
    {
      "name": "SGED",
      "value": 300
    },
    {
      "name": "SGPD",
      "value": 100
    },
    {
      "name": "SGTIC",
      "value": 100
    },
    {
      "name": "SGSP",
      "value": 100
    }      
];


  get abcData() {
    return this.abc;
  }
}