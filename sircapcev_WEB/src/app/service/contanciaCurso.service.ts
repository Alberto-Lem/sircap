import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConstanciaCurso } from '../interface/constanciaCurso.interface';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class ContanciaCursoService {

  constanciaCursoURL = environment.apiUrl + '/constanciaCurso';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<ConstanciaCurso[]> {
    return this.httpClient.get<ConstanciaCurso[]>(this.constanciaCursoURL);
  }

  public detail(id: string): Observable<ConstanciaCurso> {
    return this.httpClient.get<ConstanciaCurso>(this.constanciaCursoURL + `/${id}`);
  }

  public create(constanciaCurso: ConstanciaCurso): Observable<ConstanciaCurso> {
    return this.httpClient.post<ConstanciaCurso>(this.constanciaCursoURL, constanciaCurso);
  }
  public update(id: string, constanciaCurso: ConstanciaCurso): Observable<ConstanciaCurso> {
    return this.httpClient.put<ConstanciaCurso>(`${this.constanciaCursoURL}/${id}`, constanciaCurso);
  }

  public delete(id: string): Observable<any> {
    return this.httpClient.delete<any>(`${this.constanciaCursoURL}/${id}`);
  }
}
